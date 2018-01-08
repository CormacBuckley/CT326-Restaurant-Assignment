import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Cormac Buckley 15534413
public class Chef implements Runnable {

	private BlockingQueue bq;
	private String name;
	private int quant;
	private BlockingQueue<String> ready = new ArrayBlockingQueue<String>(1024);
	private boolean finished;
	private int burgers = 0;
	private int pizzas = 0;
	private int fish = 0;

	public Chef(BlockingQueue<String> bQueue, BlockingQueue<String> ready2, String name) {
		this.bq = bQueue;
		this.name = name;
		this.ready = ready2;
	}

	@Override
	//Take from queue, increment order counter and check type of order. Then put same order into the ready queue for the servers
	public void run() {
		while (!bq.isEmpty())
			try {
				String order = bq.take().toString();
				System.out.println("Chef " + name + " is preparing " + order);
				quant++;
				checkFood(order);
				Thread.sleep(1000);
				ready.put(order);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		if (bq.isEmpty()) {
			System.out.println(getStats());
		}
	}

	//Checking which food was ordered
	private void checkFood(String order) {
		if (order.toLowerCase().contains("burger")) {
			burgers++;
		}
		if (order.toLowerCase().contains("pizza")) {
			pizzas++;
		}
		if (order.toLowerCase().contains("fish")) {
			fish++;
		}
	}

	public String getStats() {
		String output = "Chef " + name + " has finished preparing " + quant + " orders, including " + burgers
				+ " Burgers, " + pizzas + " Pizzas and " + fish + " Fish and Chips.";

		return output;
	}
}
