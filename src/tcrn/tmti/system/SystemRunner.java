package tcrn.tmti.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.util.StopWatch;

import tcrn.tmti.config.SystemConfiguration;
import tcrn.tmti.exception.HTNSystemException;
import tcrn.tmti.metamap.MetaMapRunner;
import tcrn.tmti.ruta.RutaAnnotator;
import tcrn.tmti.systemModel.AnnotatedFile;

public class SystemRunner {

	public static void main(String[] args) {
		//String [] args = {"testText"};
		try {
			//get Configuration
			SystemConfiguration config = new SystemConfiguration();
			config = config.getPropertyValues();
			MetaMapRunner mmRunner = new MetaMapRunner(config);
			RutaAnnotator rutaAnnotator = new RutaAnnotator();
			OutputWriter outWriter = new OutputWriter(config.getOutputDirectoryPath(),"Output.csv");
			outWriter.writeHeader();
			String inputFilesPath = null;
			if(args.length>0){
				inputFilesPath = args[0];
			}else if(!(config.getInputDirectoryPath().equals(""))){
				inputFilesPath = config.getInputDirectoryPath();
			}else{
				throw new HTNSystemException("Not a Valid Input Directory");
			}
			File[] inputFiles = new File(inputFilesPath).listFiles();
			System.out.println("processing "+inputFiles.length+ " Files");
			long startTime = System.currentTimeMillis();
			for (File inFile : inputFiles) {
				AnnotatedFile annFile = new AnnotatedFile(inFile.getName());
				annFile.setDocText(getDocumentText(inFile));
				annFile = mmRunner.process(annFile);
				annFile = rutaAnnotator.annotate(annFile);
				outWriter.write(annFile);
			}
			long endTime = System.currentTimeMillis();
			long totalTime = (endTime - startTime)/1000;
			
			System.out.println("Processing complete : Output written to "+config.getOutputDirectoryPath()+"/output.csv in "+totalTime+" seconds");
			outWriter.getFw().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static String getDocumentText(File inFile) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(inFile));
		String line;
		String docText = "";
		while ((line=br.readLine())!=null) {
			docText+=line+"\n";
		}
		br.close();
		return docText;
	}

}
