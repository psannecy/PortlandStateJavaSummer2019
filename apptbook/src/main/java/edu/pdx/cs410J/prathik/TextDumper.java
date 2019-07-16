package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.FileWriter;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class TextDumper implements AppointmentBookDumper<AppointmentBook> {

    String textFile = "";

    TextDumper(String textFile) {
        super();

        if (!CheckValidFileName(textFile))
            throw new InvalidFileName("Please provide a valid file name");

        this.textFile = textFile;
    }


    private boolean CheckValidFileName(String textFile){
        return !((textFile == null) || (textFile.equals("")));
    }

    public void dump(AppointmentBook appointmentBook) {
        if (!CheckValidFileName(this.textFile))
            throw new InvalidFileName("Please provide a valid file name using SetFile method");
        
        File f = new File(this.textFile);
        // If textFiles exists, delete and then recreate it
        if (f.isFile()) {
            f.delete();
        }
        try {
            f.createNewFile();


            FileWriter fw = new FileWriter(textFile);

            fw.write(appointmentBook.getOwnerName() + "\n");
            for (Appointment appointment : appointmentBook.getAppointments())
                fw.write(appointment.getDescription() + "\t" + appointment.getBeginTimeString() + "\t" + appointment.getEndTimeString() + "\n");

            fw.close();
        } catch (Exception e) {
            throw new InvalidFileName("Please provide a valid file name using SetFile method");
        }
    }
}
