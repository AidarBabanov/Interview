package kz.naimi.interview;

import android.os.AsyncTask;
import android.util.Log;
import com.jcraft.jsch.*;

public class SendVideoToSFTPServerAsyncTask extends AsyncTask<Void, Void, Void> {

    private String filepath = "";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            JSch ssh = new JSch();
            Session session = ssh.getSession("root", "138.68.68.73", 22);
            // Remember that this is just for testing and we need a quick access, you can add an identity and known_hosts file to prevent
            // Man In the Middle attacks
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword("0746f0530fb81a67162b5b70cf");

            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;
            Log.i("Connected", "Connected mother fucker");
            //sftp.cd();
            // If you need to display the progress of the upload, read how to do it in the end of the article

            // use the put method , if you are using android remember to remove "file://" and use only the relative path
            sftp.put(filepath, "");

            Boolean success = true;

            if(success){
                // The file has been uploaded succesfully
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            Log.e("JSch", e.getMessage());
            e.printStackTrace();
        } catch (SftpException e) {
            Log.e("Sftp", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
