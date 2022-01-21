package main.controller;

import main.model.Genre;
import main.model.Song;
import main.service.GenreService;
import main.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SongController {
    @Autowired
    SongService songService;

    @Autowired
    GenreService genreService;

    @GetMapping("/home")
    public ModelAndView show(){
        List<Song> listSong = songService.findAll();
        List<Genre> listGenre = genreService.findAll();
        ModelAndView mav = new ModelAndView("homepage");
        mav.addObject("songs",listSong);
        mav.addObject("genres",listGenre);
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView addForm(){
        Song song = new Song();
        List<Genre> list = genreService.findAll();
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("song",song);
        mav.addObject("genres",list);
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView add(Song song, int genreId, MultipartFile upSong){
        Genre genre = new Genre();
        genre.setId(genreId);
        song.setGenre(genre);
        String songName = upSong.getOriginalFilename();
        try {
            FileCopyUtils.copy(upSong.getBytes(), new File("C:/HocTap/Module4/Bai5/mp3/src/main/webapp/songs/" + songName));
            String name = "/songs/" + songName;
            song.setSong(name);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }
        songService.save(song);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable int id){
        Song song = songService.findById(id);
        List<Genre> list = genreService.findAll();
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("song",song);
        mav.addObject("genres",list);
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable int id){
        Song song = songService.findById(id);
        List<Genre> list = genreService.findAll();
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("song",song);
        mav.addObject("genres",list);
        return mav;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id){
        songService.delete(id);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/play/{id}")
    public ModelAndView play(@PathVariable int id){
        Song song = songService.findById(id);
        List<Genre> list = genreService.findAll();
        ModelAndView mav = new ModelAndView("play");
        mav.addObject("song",song);
        mav.addObject("genres",list);
        return mav;
    }
}
