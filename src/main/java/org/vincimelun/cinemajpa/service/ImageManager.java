package org.vincimelun.cinemajpa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageManager {
    @Value("${cinema.img.path}")
    String path;

    public int savePhoto(Personne p, InputStream fi){
        p.setPhoto(save("p", "personnes", fi));
        return 0;
    }


    public int savePoster(Film f, InputStream is){
        f.setAfficheNom(save("f", "affiches", is));
        return 0;
    }


    private String save(String prefix, String subPath, InputStream fi){
        String fileName = "";
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path+"/"+subPath),prefix+"*")){

            for (Path file: dir
            ) {
                if(fileName.compareTo(file.getFileName().toString())<0){
                    fileName = file.getFileName().toString();
                }
            }
            String numStr = fileName.substring(1, fileName.indexOf(".jpg"));

            Integer num = Integer.parseInt(numStr);

            numStr = String.format("%04d",num+1);

            fileName = prefix+numStr+".jpg";

            String filePath = path+"/"+subPath+"/"+fileName;

            Files.copy(fi, new File(filePath).toPath());

        }catch (IOException ioe){
            System.out.println("Erreur sur nom d'image : "+ioe.getMessage());
        }


        return fileName;
    }

    public InputStream retreivePhoto(String fileName){
        return retreiveImage("photos", fileName);
    }

    public InputStream retreivePoster(String fileName){
        return retreiveImage("affiches", fileName);
    }

    private InputStream retreiveImage(String subPath, String fileName){
        InputStream is = null;
        try {
            is = new FileInputStream(path+"/"+subPath+"/"+fileName);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erreur récupération de l'image "+fileName+" : "+fnfe.getMessage());
        }
        return is;
    }
}
