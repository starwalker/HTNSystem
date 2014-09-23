package tcrn.tmti.systemModel;

import java.util.List;

public class AnnotatedFile {
	private String fileName;
	private List<Annotation> metaMapAnn;
	private List<Annotation> rutaAnn;
	private String docText;
	
	public AnnotatedFile(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Annotation> getMetaMapAnn() {
		return metaMapAnn;
	}
	public void setMetaMapAnn(List<Annotation> metaMapAnn) {
		this.metaMapAnn = metaMapAnn;
	}
	public List<Annotation> getRutaAnn() {
		return rutaAnn;
	}
	public void setRutaAnn(List<Annotation> rutaAnn) {
		this.rutaAnn = rutaAnn;
	}
	public String getDocText() {
		return docText;
	}
	public void setDocText(String docText) {
		this.docText = docText;
	}

}
