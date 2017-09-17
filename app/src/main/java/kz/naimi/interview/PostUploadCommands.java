package kz.naimi.interview;

import android.os.AsyncTask;
import android.util.Log;
import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class PostUploadCommands extends AsyncTask<Void, Void, Void> {


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

            try {
                session.connect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            try {
                sftp.cd("/home");
            } catch (SftpException e) {
                e.printStackTrace();
            }
            boolean allUploaded = false;
            while (!allUploaded) {
                allUploaded = true;
                List<InterviewElement> interview = SingletonVariableShare.getInstance().getInterview();
                for (InterviewElement i : interview) {
                    if (!i.isUploaded()) {
                        allUploaded = false;
                        break;
                    }
                }
            }
            Log.e("Files uploaded?", "True");

            InputStream in;
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            try {
                in = channelExec.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<InterviewElement> interview = SingletonVariableShare.getInstance().getInterview();
            String commandForAll = "";
            String concat ="";

            for (InterviewElement i : interview) {
                String command = "ffmpeg -i " + i.getVideoAnswer() + " -vf drawtext=\"fontfile=/path/to/font.ttf: \\\n" +
                        "text='" + i.getQuestion() + "': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5: \\\n" +
                        "boxborderw=5: x=(w-text_w)-40: y=(h-text_h)-40\" -codec:a copy -bsf:v h264_mp4toannexb -f mpegts o" + i.getVideoAnswer()+".ts ; ";
                commandForAll=commandForAll+command;
                concat=concat+"o"+i.getVideoAnswer()+".ts|";
            }
            commandForAll = commandForAll+"ffmpeg -i \"concat:"+concat+"\" -c copy -bsf:a aac_adtstoasc output.mp4;";
            channelExec.setCommand(commandForAll);
            channelExec.connect();
            Log.e("Command", "Completed");
            channelExec.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return null;
    }
}

