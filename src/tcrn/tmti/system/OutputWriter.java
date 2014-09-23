package tcrn.tmti.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tcrn.tmti.systemModel.AnnotatedFile;
import tcrn.tmti.systemModel.Annotation;

public class OutputWriter {
	
	private FileWriter fw ;

	public FileWriter getFw() {
		return fw;
	}

	public void setFw(FileWriter fw) {
		this.fw = fw;
	}

	public OutputWriter(String directoryPath , String fileName) throws Exception {
		super();
		File outFile = new File(directoryPath,fileName);
		this.fw = new FileWriter(outFile);
	}

	public void write(AnnotatedFile annFile) throws Exception {
		for (Annotation annotation : annFile.getMetaMapAnn()) {
			fw.write(annFile.getFileName()+",\""+annotation.getAnnotatedText()+"\","+annotation.getStartOffset()+","+annotation.getEndOffset()+"\n");
		}
		for (Annotation annotation : annFile.getRutaAnn()) {
			fw.write(annFile.getFileName()+",\""+annotation.getAnnotatedText()+"\","+annotation.getStartOffset()+","+annotation.getEndOffset()+"\n");
		}	
	}

	/**
	 * @throws IOException 
	 * 
	 */
	public void writeHeader() throws IOException {
		this.getFw().write("FileName,AnnotatedText,StartOffset,EndOffset\n");
		
	}
	

}
