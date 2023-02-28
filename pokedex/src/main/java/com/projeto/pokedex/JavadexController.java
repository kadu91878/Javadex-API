package com.projeto.pokedex;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavadexController {

    @GetMapping("/javadex")
    public int[] javadex(@RequestParam("pokemon1") String pokemon1,
            @RequestParam("pokemon2") String pokemon2) {
        Javadex javadex = new Javadex();
        return javadex.javadex(pokemon1, pokemon2);
    }



    @PostMapping("/javadex")
    public int[] javadex(@RequestBody RequestPayload requestPayload) {
        String pokemon1 = requestPayload.getPokemon1();
        String pokemon2 = requestPayload.getPokemon2();

        Javadex javadex = new Javadex();
        return javadex.javadex(pokemon1, pokemon2);
    }

    public static class RequestPayload {
        private String pokemon1;
        private String pokemon2;

        public String getPokemon1() {
            return pokemon1;
        }

        public void setPokemon1(String pokemon1) {
            this.pokemon1 = pokemon1;
        }

        public String getPokemon2() {
            return pokemon2;
        }

        public void setPokemon2(String pokemon2) {
            this.pokemon2 = pokemon2;
        }
    }
}