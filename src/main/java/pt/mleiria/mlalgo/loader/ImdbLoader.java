/**
 * 
 */
package pt.mleiria.mlalgo.loader;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Paths.get;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pt.mleiria.mlalgo.dataset.vo.ImdbVO;

/**
 * @author manuel
 *
 */
public class ImdbLoader {
    
    private static final Logger LOG = Logger.getLogger(ImdbLoader.class.getName());

    public static List<ImdbVO> load(String path) {
	final Path file = get(path);
	final List<ImdbVO> dataSet = new ArrayList<>();
	try (final InputStream in = newInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
	    String line;
	    /*
	     * skip first row
	     */
	    reader.readLine();
	    while ((line = reader.readLine()) != null) {
		//String[] data = line.split(",");
		String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		final ImdbVO instance = new ImdbVO();
		instance.setData(data);
		dataSet.add(instance);
		LOG.info(instance.toString());
	    }
	} catch (final IOException x) {
	    LOG.severe(x.getMessage());
	} catch (final Exception e) {
	    LOG.severe(e.getMessage());
	    e.printStackTrace();
	}

	return dataSet;
    }
}
