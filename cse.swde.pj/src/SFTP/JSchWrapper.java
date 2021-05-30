/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SFTP;

/**
 *
 * @author 김기태
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchWrapper {

    private Session jschSession = null;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;

    /**
     * 파일 업로드
     *
     * @param fileName
     * @param dirPath
     * @throws Exception
     */
    public boolean uploadFile(String fileName, String dirPath) throws Exception {
        boolean isSuccess = false;

        FileInputStream fis = null;

        try {
            // 대상폴더 이동
            channelSftp.cd(dirPath);

            File file = new File(fileName);
            fis = new FileInputStream(file);

            // 파일 업로드
            channelSftp.put(fis, file.getName());
            isSuccess = true;

            System.out.println("File uploaded : " + file.getAbsolutePath() + " => " + dirPath + "/" + file.getName());

        } catch (Exception e) {
            throw e;

        } finally {
            close(fis);
        }

        return isSuccess;
    }

    /**
     * 파일 다운로드
     *
     * @param remoteFilePath
     * @param localDirPath
     * @param overwrite
     * @return
     * @throws Exception
     */
    public boolean downloadFile(String remoteFilePath, String localDirPath, boolean overwrite) throws Exception {
        if (remoteFilePath == null || remoteFilePath.length() == 0) {
            return false;
        }

        boolean isSuccess = false;

        byte[] buffer = new byte[1024];

        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            if (remoteFilePath.indexOf("\\") > -1) {
                remoteFilePath = remoteFilePath.replace("\\", "/");
            }

            String remoteFileName = "";

            // 대상폴더 이동
            int lastSlashIndex = remoteFilePath.lastIndexOf("/");
            if (lastSlashIndex > -1) {
                String cdDir = remoteFilePath.substring(0, lastSlashIndex);
                remoteFileName = remoteFilePath.substring(lastSlashIndex + 1);
                channelSftp.cd(cdDir);
            } else {
                remoteFileName = remoteFilePath;
                channelSftp.cd("/");
            }

            File destFile = new File(localDirPath + File.separator + remoteFileName);
            if (destFile.exists()) {
                if (overwrite) {
                    destFile.delete();
                } else {
                    System.out.println("File Download canceled. File already exists : " + destFile.getAbsolutePath());
                    return false;
                }
            }

            // 파일 다운로드
            bis = new BufferedInputStream(channelSftp.get(remoteFileName));
            fos = new FileOutputStream(destFile);
            bos = new BufferedOutputStream(fos);
            int readCount = 0;
            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }

            isSuccess = true;
            System.out.println("File downloaded : " + remoteFilePath + " => " + destFile.getAbsolutePath());

        } catch (Exception e) {
            throw e;

        } finally {
            close(bos);
            close(fos);
            close(bis);
        }

        return isSuccess;
    }

    /**
     * 폴더 생성
     *
     * @param dirPath
     * @param dirName
     * @throws Exception
     */
    public boolean mkdir(String dirPath, String dirName) throws Exception {
        boolean isSuccess = false;

        String destDirPath = dirPath + "/" + dirName;

        boolean destDirExists = false;

        try {
            channelSftp.cd(destDirPath);
            destDirExists = true;

        } catch (Exception e) {
            destDirExists = false;
        }

        if (destDirExists) {
            System.out.println("Folder Creation canceled. Folder already exists : " + destDirPath);
            return false;
        }

        // 대상폴더 이동
        channelSftp.cd(dirPath);

        // 폴더 생성
        channelSftp.mkdir(dirName);
        isSuccess = true;

        System.out.println("Folder created : " + destDirPath);
        return isSuccess;
    }

    /**
     * SFTP 접속하기
     *
     * @return
     * @throws JSchException
     * @throws Exception
     */
    public void connectSFTP(String host, int port, String userName, String password) throws Exception {
        // JSch 객체를 생성
        JSch jsch = new JSch();

        // JSch 세션 객체를 생성 (사용자 이름, 접속할 호스트, 포트 전달)
        jschSession = jsch.getSession(userName, host, port);

        // 패스워드 설정
        jschSession.setPassword(password);

        // 기타설정 적용
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);

        // 접속
        jschSession.connect();

        // sftp 채널 열기
        channel = jschSession.openChannel("sftp");

        // sftp 채널 연결
        channelSftp = (ChannelSftp) channel;
        channelSftp.connect();
    }

    /**
     * SFTP 접속해제
     */
    public void disconnectSFTP() {
        try {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        } catch (Exception e) {
        } finally {
            channelSftp = null;
        }

        try {
            if (channel != null && channel.isConnected()) {
                channel.disconnect();
            }
        } catch (Exception e) {
        } finally {
            channel = null;
        }

        try {
            if (jschSession != null && jschSession.isConnected()) {
                jschSession.disconnect();
            }
        } catch (Exception e) {
        } finally {
            jschSession = null;
        }
    }

    /**
     * FileInputStream 객체 닫기
     *
     * @param fis
     */
    private void close(FileInputStream fis) {
        try {
            if (fis != null) {
                fis.close();
            }
        } catch (Exception e) {
        } finally {
            fis = null;
        }
    }

    /**
     * BufferedInputStream 객체 닫기
     *
     * @param bis
     */
    private void close(BufferedInputStream bis) {
        try {
            if (bis != null) {
                bis.close();
            }
        } catch (Exception e) {
        } finally {
            bis = null;
        }
    }

    /**
     * FileOutputStream 객체 닫기
     *
     * @param fos
     */
    private void close(FileOutputStream fos) {

        try {
            if (fos != null) {
                fos.flush();
            }
        } catch (Exception e) {
        }

        try {
            if (fos != null) {
                fos.close();
            }
        } catch (Exception e) {
        } finally {
            fos = null;
        }
    }

    /**
     * BufferedOutputStream 객체 닫기
     *
     * @param bos
     */
    private void close(BufferedOutputStream bos) {

        try {
            if (bos != null) {
                bos.flush();
            }
        } catch (Exception e) {
        }

        try {
            if (bos != null) {
                bos.close();
            }
        } catch (Exception e) {
        } finally {
            bos = null;
        }
    }
}
