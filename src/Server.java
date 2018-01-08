import java.util.concurrent.BlockingQueue;

//Cormac Buckley 15534413
public class Server implements Runnable {

	private int quant;
	protected BlockingQueue blockingQueue;
	private String name;
	public boolean finished = false;
	private int burgers;
	private int pizzas;
	private int fish;

	public Server(BlockingQueue<String> bQueue, String name) {
		this.blockingQueue = bQueue;
		this.name = name;
	}

	@Override
	//Take from queue, increment counter and check order type
	public void run() {
		do {
			try {
				Thread.sleep(1000);
				if (blockingQueue.peek() != null) {
					String next = blockingQueue.take().toString();
					System.out.println("Server " + name + " is serving " + next);
					quant++;
					checkFood(next);
				}
				Thread.sleep(500);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!blockingQueue.isEmpty());
		this.finished = true;
		System.out.println(getStats());
	}

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
		String output = "Server " + name + " has finished preparing " + quant + " orders, including " + burgers
				+ " Burgers, " + pizzas + " Pizzas and " + fish + " Fish and Chips.";

		return output;
	}

}
