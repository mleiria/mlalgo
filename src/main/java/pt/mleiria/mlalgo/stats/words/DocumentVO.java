package pt.mleiria.mlalgo.stats.words;

import java.io.File;
import java.io.Serializable;

public class DocumentVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8437986708091974564L;
	private final String filePath;
    private final String fileName;
    private final String fileExtension;
    private final String completePath;
    
    /**
     * 
     * @param path
     */
    public DocumentVO(final String path) {
    	this.completePath = path;
		final String[] tmp = path.split(File.separator);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tmp.length-1; i++) {
			sb.append(tmp[i]).append(File.separator);
		}
		filePath = sb.toString();
		fileName = tmp[tmp.length-1];
		fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public String getCompletePath() {
		return completePath;
	}

	@Override
	public String toString() {
		return "DocumentVO [filePath=" + filePath + ", fileName=" + fileName + ", fileExtension=" + fileExtension
				+ ", completePath=" + completePath + "]";
	}
	
	    
    
    
}
