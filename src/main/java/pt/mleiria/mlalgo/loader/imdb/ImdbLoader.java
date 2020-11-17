/**
 *
 */
package pt.mleiria.mlalgo.loader.imdb;


import pt.mleiria.mlalgo.dataset.vo.imdb.Imdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Paths.get;

/**
 * @author manuel
 *
 */
public class ImdbLoader {

    private static final Logger LOG = Logger.getLogger(ImdbLoader.class.getName());

    private final List<Imdb> dataSet;

    /**
     *
     * @param path
     * @param clazz
     * @return
     */
    public ImdbLoader(String path, Class clazz) {
        final Path file = get(path);
        final List<Imdb> dataSet = new ArrayList<>();
        try (final InputStream in = newInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            /*
             * skip first row
             */
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                final String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                final Imdb instance = (Imdb) clazz.newInstance();
                instance.setData(data);
                dataSet.add(instance);
                //LOG.info(instance.toString());
            }
        } catch (final IOException x) {
            LOG.severe(x.getMessage());
            x.printStackTrace();
        } catch (final Exception e) {
            LOG.severe(e.getMessage());
            e.printStackTrace();
        }
        this.dataSet = dataSet;
    }

    public List<Imdb> filterByActor(String actor){
        List<Imdb> res = new ArrayList<>();

        for(Imdb imdb : dataSet){
            if(imdb.getPrincipalActor().contains(actor))
                res.add(imdb);
        }

                /*
        dataSet.stream()
                .filter(elem -> elem.getPrincipalActor().contains(actor))
                .collect(Collectors.toList());
*/
        return res;
    }



}
