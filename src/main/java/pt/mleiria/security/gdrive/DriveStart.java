package pt.mleiria.security.gdrive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import pt.mleiria.mlalgo.utils.StopWatch;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


public class DriveStart {
    private static final Logger LOGGER = Logger.getLogger(DriveStart.class.getName());
    private static final String APPLICATION_NAME = "ML Image DB";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = DriveStart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8889).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * @param service
     * @param file
     */
    private static void downloadFile(final Drive service, final File file, final String destinationDir) {
        LOGGER.info("FileName: " + file.getName() + " FileId: " + file.getId());

        try (final OutputStream outputStream = new FileOutputStream(destinationDir + "/" + file.getName())) {
            LOGGER.info(file.toPrettyString());
            service.files().get(file.getId()).executeMediaAndDownloadTo(outputStream);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }


    }

    public static boolean downloadFilesFromFolder(final String folderName, final String destinationDir) {

        final StopWatch sw = new StopWatch();
        String query = "'root' in parents and mimeType != 'application/vnd.google-apps.folder' and trashed = false";
        try {
            // Build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            //First pass: discover parentId of folderName
            FileList result = service.files()
                    .list()
                    .setQ(query)
                    .setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, parents)")
                    .execute();
            String id = "";
            for (File file : result.getFiles()) {
                final String fname = file.getName();
                if (fname.substring(0, fname.length() - 4).equals(folderName)) {
                    id = file.getParents().get(0);
                    query = query.replace("root", id);
                    break;
                }
            }
            LOGGER.info("Found File ID: " + id + " for folder: " + folderName);

            result = service.files()
                    .list()
                    .setQ(query)
                    .setSpaces("drive")
                    //.setPageSize(5)
                    .setFields("nextPageToken, files(id, name, parents)")
                    //.setFields("nextPageToken, files(*)")
                    .execute();
            final List<File> files = result.getFiles();
            if (files == null || files.isEmpty()) {
                LOGGER.info("No files found.");
            } else {
                LOGGER.info("Files:");
                files.stream().parallel().forEach(elem -> downloadFile(service, elem, folderName));
            }
            LOGGER.info("Downloaded: " + files.size() + " " + sw.elapsedTimeToString());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
