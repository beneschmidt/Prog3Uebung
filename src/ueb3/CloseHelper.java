package ueb3;

import java.io.Closeable;

public class CloseHelper {

	public static void close(Closeable closeable) {
		try {
			if(closeable!=null)
				closeable.close();
		} catch(Exception e){
		}
		
	}

}
