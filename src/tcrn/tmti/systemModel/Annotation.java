package tcrn.tmti.systemModel;

public class Annotation {
	
	String annotatedText;
	Integer startOffset;
	Integer endOffset;
	
	public String getAnnotatedText() {
		return annotatedText;
	}
	public void setAnnotatedText(String annotatedText) {
		this.annotatedText = annotatedText;
	}
	public Integer getStartOffset() {
		return startOffset;
	}
	public void setStartOffset(Integer startOffset) {
		this.startOffset = startOffset;
	}
	public Integer getEndOffset() {
		return endOffset;
	}
	public void setEndOffset(Integer endOffset) {
		this.endOffset = endOffset;
	}
	
	

}
