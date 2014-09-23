package tcrn.tmti.metamap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import tcrn.tmti.config.SystemConfiguration;
import tcrn.tmti.systemModel.AnnotatedFile;
import tcrn.tmti.systemModel.Annotation;

public class MetaMapRunner {

	private SystemConfiguration sysConfiguration;

	public SystemConfiguration getSysConfiguration() {
		return sysConfiguration;
	}

	public void setSysConfiguration(SystemConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}

	public MetaMapRunner(SystemConfiguration sysConfiguration) {
		this.sysConfiguration = sysConfiguration;
	}
	/*
	 * Processes Metamap using MetaMap UIMA annotator
	 * */
	/**
	 * @param inFile
	 * @param annotatedFile
	 * @return {@link AnnotatedFile}
	 * @throws Exception
	 * @throws Exception
	 */
	public AnnotatedFile process(AnnotatedFile annotatedFile) throws Exception, Exception {

		AnalysisEngine ae = AnalysisEngineFactory.createEngine("desc/MetaMapApiAE");
		JCas jCas = ae.newJCas();
		jCas.setDocumentText(annotatedFile.getDocText());
		ae.process(jCas);

		Collection<AnnotationFS> select = CasUtil.select(jCas.getCas(), CasUtil.getType(jCas.getCas(), "org.metamap.uima.ts.Candidate"));
		Integer firstStart = 0;
		Integer firstend = 0;
		List<Annotation> metaMapAnnList = new ArrayList<Annotation>();
		for (AnnotationFS annotationFS : select) {
			String  coveredText  = annotationFS.getCoveredText().replaceAll("\\n", " ");
			Integer startOffset  = annotationFS.getBegin();
			Integer endOffset    = annotationFS.getEnd();
			String cui = annotationFS.getStringValue(CasUtil.getType(jCas.getCas(), "org.metamap.uima.ts.Candidate").getFeatureByBaseName("cui"));
			if(!(firstStart.equals(startOffset)) && !(firstend.equals(endOffset))){
				boolean found = filterCui(cui);
				if(found){
					//section info
					Annotation ann = new Annotation();
					ann.setAnnotatedText(coveredText);
					ann.setStartOffset(startOffset);
					ann.setEndOffset(endOffset);
					metaMapAnnList.add(ann);
				}
			}
			firstStart = startOffset;
			firstend = endOffset;

		}
		annotatedFile.setMetaMapAnn(metaMapAnnList);
		return annotatedFile;

	}
	private static boolean filterCui(String cui) {
		/**
		 * 
		 *  Hypertension C0020538	 
			Benign hypertension	C0264637	 
			Essential hypertension	C0085580	 
			Endocrine hypertension	C0264641	 
			Malignant hypertension	C0020540	 
			Systolic hypertension	C0221155	 
			Diastolic hypertension	C0235222	 
			Secondary hypertension	C0155616	 
			Secondary benign hypertension	C0155620	 
			Malignant secondary hypertension	C0155617	 
			Secondary diastolic hypertension	C0264647

		 * **/
		String [] cuiList = {"C0020538","C0264637","C0085580","C0264641","C0020540","C0221155","C0235222","C0155616","C0155620","C0155617","C0264647"};
		for (String cuiInList : cuiList) {
			if(cui.equals(cuiInList)){
				return true;
			}
		}
		return false;
	}
	


}
