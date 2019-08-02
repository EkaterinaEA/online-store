package info.sjd.service;

import info.sjd.model.Item;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxbWorker {

    private final static String MAIN_DIR = System.getProperty("user.dir");
    private final static String SEPARATOR = System.getProperty("file.separator");

    private final static String filePath = MAIN_DIR + SEPARATOR + "data" + SEPARATOR + "item.xml";

    public static void convertObjectToXml(Item item){

        try {
            JAXBContext context = JAXBContext.newInstance(Item.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(item, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
