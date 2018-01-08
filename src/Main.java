import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//Cormac Buckley 15534413
public class Main {

	public static void main(String[] args) throws Exception {
//I used 2 arrays, one for the chefs and the other for the servers
		BlockingQueue<String> orders = new ArrayBlockingQueue<String>(1024);
		BlockingQueue<String> ready = new ArrayBlockingQueue<String>(1024);

		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader("orderList.txt");
			br = new BufferedReader(fr);
			String txt;
			while ((txt = br.readLine()) != null) {
				orders.add(txt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			//Passed both arrays to chefs so that ready can be populated
		Chef john = new Chef(orders, ready, "John");
		Chef mark = new Chef(orders, ready, "Mark");

		Server katie = new Server(ready, "Katie");
		Server andrew = new Server(ready, "Andrew");
		Server emily = new Server(ready, "Emily");

		new Thread(john).start();
		new Thread(mark).start();

		new Thread(katie).start();
		new Thread(andrew).start();
		new Thread(emily).start();
	}

}
