package testgroup.service.ParseReplacement;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;
import testgroup.model.Replacement;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

@Service
public class ConnectionParse {
    private ArrayList<Replacement> replacements;
    public ArrayList<Replacement> connection(){
        replacements = new ArrayList<>();
        final String host = "smtp.gmail.com";
        final String username = "javaboot871@gmail.com";
        final String password = "ret345wer";
        return check(host, username, password);
    }
    private ArrayList<Replacement>  check(String host, String user, String password){
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("pop3s");
            store.connect(host, user, password);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            if ( messages.length>0) {
                Message message = messages[0];
                parseMassage(message);
            }
            emailFolder.close(false);
            store.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return replacements;
    }
    private void parseMassage(Message message) throws Exception {
        Multipart mp = (Multipart) message.getContent();
        BodyPart part ;
        try {
            part = mp.getBodyPart(1);
        }catch (Exception e) {
            return;
        }
        if(Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())){
            String fileName = MimeUtility.decodeText(part.getFileName());
            InputStream is = part.getInputStream();
            System.out.println(fileName);
            File file = new File("replacement.xls");
            try(OutputStream outputStream = new FileOutputStream(file)){
                IOUtils.copy(is, outputStream);
            } catch (FileNotFoundException e) {
                return;
            }
            replacements = new EnterPoint().parse(file);
        }
        else {
            message.setFlag(Flags.Flag.DELETED, true);
            System.out.println("DELETED");
        }
    }
}
