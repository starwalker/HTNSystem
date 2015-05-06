package tcrn.tmti.ruta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import tcrn.tmti.systemModel.AnnotatedFile;
import tcrn.tmti.systemModel.Annotation;

public class RutaAnnotator {

	/**
	 * @param  {@link AnnotatedFile}
	 * @return {@link AnnotatedFile}
	 * @throws Exception;   
	 */
	public AnnotatedFile annotate(AnnotatedFile annFile) throws  Exception {
		AnalysisEngine engine = AnalysisEngineFactory.createEngine("BPvalueExtractorEngine");
			CAS cas = engine.newCAS();
			cas.setDocumentText(annFile.getDocText());
			engine.process(cas);
			TypeSystem ts = cas.getTypeSystem();  
			Iterator<Type> typeItr = ts.getTypeIterator();
			List<Annotation> annList = new ArrayList<Annotation>();
			while (typeItr.hasNext()) {
				Type type = (Type) typeItr.next();
				if (type.getName().equals("tmti.BpAnnotation")) {
					AnnotationIndex<AnnotationFS> annotations2 = cas.getAnnotationIndex(type);
					for (AnnotationFS afs : annotations2)
					{
						String annText   = afs.getCoveredText();
						annText = annText.replaceAll("\\n", " ");
						Integer startOffset = afs.getBegin();
						Integer endOffset = afs.getEnd();
						Annotation ann = new Annotation();
						ann.setAnnotatedText(annText);
						ann.setStartOffset(startOffset);
						ann.setEndOffset(endOffset);
						annList.add(ann);
					}
				}
			}
			annFile.setRutaAnn(annList);
			return annFile;
		
	}
	
	
}
