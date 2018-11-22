package br.com.joao_parana.util;

// import br.com.joao_parana.common.Configuration;
// import org.apache.log4j.LogManager;
// import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileUtil {

    //private static Logger logger = LogManager.getLogger(FileUtil.class);

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String PATH_SEPARATOR = System.getProperty("path.separator");

    public static Properties getPropertiesFromFile(File file)
        throws IOException, business.ApplicationException {

        Properties properties = new Properties();

        FileInputStream fis = null;

        try {
            String fileName = file.getName();
            String directory = file.getParent();

            try {
                fis = new FileInputStream(file);

            } catch (Exception e) {
                throw new business.ApplicationException("Não foi possivel abrir o arquivo '" + fileName
                    + "' no diretório " + directory);
            }

            if (fis == null) {
                throw new business.ApplicationException("Não foi possivel abrir o arquivo '" + fileName
                    + "' no diretório " + directory);
                // return null;
            } else {

                // logger.info("Carregando o arquivo '" + fileName
                //    + "' no diretório " + directory);
                properties.load(fis);
                // logger.info("Arquivo '" + fileName + "' no diretório "
                //    + directory + " carregado.");
            }
        } catch (IOException e) {
            throw e;
        }

        return properties;
    }

    public static void traverse(File f) {
        process(f);
        if (f.isDirectory()) {
            String[] children = f.list();
            for (int i = 0; i < children.length; i++) {
                traverse(new File(f, children[i]));
            }
        }
    }

    /**
     * @param path           diretório
     * @param fileNameSuffix extensão do arquivo
     * @return lista de arquivos que pertencem ao diretório informado
     */
    public static List<File> getFileList(File path, String fileNameSuffix) {

        if (path.exists()) {
            if (!path.isDirectory()) {
                throw new RuntimeException("O caminho informado '"
                    + path.getPath() + "' deve ser um diretório.");
            }
        } else {
            throw new RuntimeException(
                "O caminho informado '" + path.getPath() + "' não existe.");
        }

        List<File> fileVector = new Vector<File>();

        String[] children = path.list();

        if (children == null) {
            throw new RuntimeException(
                "Erro ao tentar ler conteúdo do caminho informado: '"
                    + path.getPath() + "'.");
        }

        // logger.debug("path=" + path + "  children.length=" + children.length);

        for (int i = 0; i < children.length; i++) {
            // trata apenas arquivos com o padrão informado.
            if (children[i].toLowerCase()
                .endsWith(fileNameSuffix.toLowerCase())) {
                fileVector.add(new File(path, children[i]));
            }
        }

        return fileVector;
    }

    private static void process(File f) {
        // logger.debug(f.getName() + ((f.isDirectory()) ? " - DIR " : " - FILE"));
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public static void createDirTree(String path) {
        // Create a directory; all non-existent ancestor directories are
        // automatically created
        // logger.debug("diretorio não existente será criado : " + path);
        boolean success = (new File(path)).mkdirs();
        if (!success) {
            throw new RuntimeException(
                "Falha na criação do diretório : " + path);
        }
    }

    /**
     * @param file
     * @return uma <code>String</code> com o conteúdo do arquivo
     * @throws IOException
     * @throws IOException
     */
    public static String getContent(File file) throws IOException, business.ApplicationException {

        StringBuffer buf = new StringBuffer();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            String currentDir = new File(".").getAbsoluteFile()
                .getAbsolutePath();
            String msg = "currentDir=" + currentDir + " -> " + e.getMessage();
            throw new business.ApplicationException(msg+ " "+ e);
            // throw new FileNotFoundException(msg);
        }

        try {
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    buf.append(line);
                }
            } catch (IOException e) {
                throw new business.ApplicationException(e.getMessage() +" "+ e);
                // throw new IOException(e.getMessage());
            }
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new business.ApplicationException(e.getMessage() +" "+ e);
                // throw new IOException(e.getMessage());
            }
        }
        return buf.toString();
    }

    public static File createNewFile(String fileNamePrefix, String fileNameSuffix, String fileContent) throws IOException, business.ApplicationException  {
        return createNewFile(".", fileNamePrefix, fileNameSuffix, true, fileContent);
    }

    public static File createNewFile(String fileDir, String fileNamePrefix,
                                     String fileNameSuffix, boolean overwrite, String fileContent)
        throws IOException, business.ApplicationException {

        File myFile = null;
        File fileToWrite = null;
        File myDir = new File(fileDir);

        if (!myDir.exists()) {
            createDirTree(fileDir);
        }

        myFile = new File(
            (myDir.getAbsolutePath() + FILE_SEPARATOR
                + fileNamePrefix + "." + fileNameSuffix));
        if (myFile.exists() && !overwrite) {
            throw new business.ApplicationException(
                "Arquivo '" + myFile.getAbsolutePath() + "' Já existe");
            // return null;
        }

        fileToWrite = myFile;

        if (myFile.exists() && overwrite) {
            fileToWrite = new File(
                (myDir.getAbsolutePath() + FILE_SEPARATOR
                    + fileNamePrefix + "." + fileNameSuffix + "-temp"));

        }

        BufferedWriter out = new BufferedWriter(
            new FileWriter(fileToWrite, false));
        out.write(fileContent);
        out.close();

        if (!fileToWrite.toPath().equals(myFile.toPath())) {
            try {
                Files.move(fileToWrite.toPath(), myFile.toPath(),
                    StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException
                | FileAlreadyExistsException e) {
                Files.move(fileToWrite.toPath(), myFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            }
        }

        return myFile;
    }

    /**
     * @param fileNamePrefix
     * @param fileNameSuffix
     * @param fileContent
     * @return um objeto <code>File</code> contendo fileContent
     */
    public static File createTempFile(String fileNamePrefix,
                                      String fileNameSuffix, String fileContent) throws  business.ApplicationException {

        File tempFile = null;

        try {
            // Create temp file.
            tempFile = File.createTempFile(fileNamePrefix, fileNameSuffix);
            // Delete temp file when program exits.
            tempFile.deleteOnExit();
            // Write to temp file
            BufferedWriter out = new BufferedWriter(new FileWriter(tempFile));
            out.write(fileContent);
            out.close();
        } catch (IOException e) {
            throw new business.ApplicationException(e.getMessage() + " "+ e);
            // return null;
        }

        return tempFile;
    }

    /**
     * @param file
     * @return the contents of the file in a byte array.
     * @throws IOException
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new RuntimeException("Arquivo muito grande. "
                + "Não é possível realizar a leitura");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset,
            bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException(
                "Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public static List<String> getFileNameListFromZip(String zipFilename) throws business.ApplicationException  {
        List<String> ret = new ArrayList<String>();

        try { // Open the ZIP file
            ZipFile zf = new ZipFile(zipFilename); // Enumerate each entry
            for (Enumeration entries = zf.entries(); entries
                .hasMoreElements(); ) {
                // Get the entry name
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();

                ret.add(zipEntry.getName());
            }
        } catch (IOException e) {
            throw new business.ApplicationException(e.getLocalizedMessage() +" "+ e);
        }
        return ret;
    }

    public static boolean exists(String filename) {

        boolean exists = (new File(filename)).exists();

        return exists;
    }

    public static ZipInputStream getInputStreamFromZipFile(String zipFilename) throws  business.ApplicationException {

        ZipInputStream inputStream = null;

        try {
            inputStream = new ZipInputStream(new FileInputStream(zipFilename));
        } catch (FileNotFoundException e) {
            throw new business.ApplicationException(e.getLocalizedMessage()+" "+ e);
        }

        return inputStream;
    }

    public static void main(String[] args) throws  business.ApplicationException  {
        String filename = "C:\\Projetos\\SOMA\\soma\\data\\00\\00\\00\\00\\00\\00\\0d\\teste.zip";
        List<String> ls = getFileNameListFromZip(filename);
        System.out.println(ls);
        OutputStream outputStream = null;
        try {
            ZipInputStream in = getInputStreamFromZipFile(filename);

            ZipEntry zipEntry = in.getNextEntry();
            while (zipEntry != null) {
                if (zipEntry.getName().equals("mab_raw_3338.ser")) {

                    outputStream = new BufferedOutputStream(
                        new ByteArrayOutputStream());
                    byte[] buf = new byte[2048];
                    int len = 0;
                    while ((len = in.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }

                    ObjectInputStream objectInputStream = new ObjectInputStream(
                        new ByteArrayInputStream(buf));

                    double[] values = (double[]) objectInputStream.readObject();
                    StringBuffer sb = new StringBuffer();
                    for (double d : values) {
                        sb.append(d + " ");
                    }
                    objectInputStream.close();

                    System.out.println(sb.toString());
                }

                zipEntry = in.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
