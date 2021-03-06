package util;

import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.stream.Collectors;

public class XmlSerializer {

    public static <T> boolean save(T object, String file) {
        return save(object, file, object.getClass());
    }

    /**
     * Saves an object serialized to an XML file
     * @param object object to serialize
     * @param file target file
     * @param classes contextual classes needed
     * @param <T> object class
     * @return
     */
    public static <T> boolean save(T object, String file, Class... classes){
        try {
            File yourFile = new File(file);
            yourFile.createNewFile();

            JAXBContext context = JAXBContext.newInstance(classes);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(object, yourFile);
        }catch (IllegalAnnotationsException e){
            System.out.println("XmlSerializer Load Error: "+e.getErrors().stream().map(x -> x.getMessage()).collect(Collectors.toList()));
        }catch (Exception e){
            System.out.println("XmlSerializer Save Error: "+e.getMessage());
        }

        return  true;
    }

    /**
     * Loads and de-serializes an object from XML
     * @param file target file
     * @param classes contextual classes needed
     * @param <T> object class
     * @return
     */
    public static <T> T load(String file, Class... classes){
        T obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File xml = new File(file);

            if (xml.exists()){
                obj = (T) unmarshaller.unmarshal(xml);
            }
        }catch (IllegalAnnotationsException e){
            System.out.println("XmlSerializer Load Error: "+e.getErrors().stream().map(x -> x.getMessage()).collect(Collectors.toList()));
            return null;
        }catch (Exception e){
            System.out.println("XmlSerializer Load Error: "+e.getMessage());
            return null;
        }

        return obj;
    }
}
