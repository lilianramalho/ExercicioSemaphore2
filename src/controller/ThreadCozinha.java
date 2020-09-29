package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	private int idprato;
	private Semaphore semaforo;

	public ThreadCozinha(int idprato, Semaphore semaforo) {
		this.idprato = idprato;
		this.semaforo = semaforo;
	}

	public void run() {
		if (idprato % 2 == 1) {
			sopa();
		} else {
			lasanha();
		}
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		entrega();
		}
		semaforo.release();
	}

	private void sopa() {
		int por = 0;
		double tempofinal = (double) ((Math.random() * 301) + 500);
		double tempoinicial = 0;
		System.out.println("thread#"+idprato+"sopa iniciada");
		while (tempoinicial < tempofinal) {
			por = (int) ((tempoinicial*100)/tempofinal);
			System.out.println("thread#"+idprato+" cozinhando sopa, "+por+ "% feita");
			tempoinicial += 100;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("thread#"+idprato+" sopa 100% feita encaminhando para a entrega");

	}

	private void lasanha() {
		int por = 0;
		double tempofinal = (double) ((Math.random() * 601) + 600);
		double tempoinicial = 0;
		System.out.println("thread#"+idprato+"lasanha iniciada");
		while (tempoinicial < tempofinal) {
			por = (int) ((tempoinicial*100)/tempofinal);
			System.out.println("thread#"+idprato+" cozinhando lasanha, "+por+ "% feita");
			tempoinicial += 100;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("thread#"+idprato+" lasanha 100% feita encaminhando para a entrega");

	}

	private void entrega() {
		if (idprato % 2 == 1) {
			System.out.println("thread#"+idprato+" o prato de sopa de cebola esta sendo entregue");
		} else {
			System.out.println("thread#"+idprato+" o prato de lasanha a bolanhesa esta sendo entregue");
		}
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}