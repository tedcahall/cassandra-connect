package Chapter03;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class Chapter0301 {

	private Cluster cluster;
	private Session session;

	public void connect(String node) {
		cluster = Cluster.builder()
				.addContactPoint(node).withPort(9042)
				.build();
		Metadata metadata = cluster.getMetadata();
		
		System.out.printf("Connected to cluster: %s\n", metadata.getClusterName());
		
		for (Host host: metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
		
		session = cluster.connect();
	}
	
	public void close() {
		cluster.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Chapter0301 client = new Chapter0301();

		client.connect("10.0.1.101");
		
		client.close();
	}
}
