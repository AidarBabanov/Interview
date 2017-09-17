package kz.naimi.interview;

import android.os.AsyncTask;
import android.util.Log;
import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

public class SendVideoToSFTPServerAsyncTask extends AsyncTask<Void, Void, Void> {

    private String filepath = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            JSch ssh = new JSch();
            Session session = ssh.getSession("root", "46.101.109.41", 22);
            // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
            // Man In the Middle attacks
            java.util.Properties config = new java.util.Properties();
            config.put("kex", "diffie-hellman-group1-sha1,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha1,diffie-hellman-group-exchange-sha256");
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("Kuyrdak934");

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;
            Log.i("Connected", "Connected mother fucker");
            try {
                sftp.cd("/home");
            } catch (SftpException e) {
                e.printStackTrace();
            }
            // If you need to display the progress of the upload, read how to do it in the end of the article

            // use the put method , if you are using android remember to remove "file://" and use only the relative path
            Log.i("File Path", filepath);
            File f1 = new File(URI.create(filepath));
            Log.i("File Sftp put",  f1.exists()+"");

            try {
                sftp.put(new FileInputStream(f1), f1.getName());
            }catch(Exception e){
                Log.i("File Sftp put", e.getMessage());
            }
            //sftp.put(filepath, "");

            Boolean success = true;

            if(success){
                // The file has been uploaded succesfully
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            Log.e("JSch", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
