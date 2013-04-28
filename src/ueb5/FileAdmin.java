package ueb5;

public interface FileAdmin {

	public String getFileName();
	public String getFileContent();
	public void setFileName(String fileName);
	public void setFileContent(String fileContent);
	public void setError(String errorMessage);
}
