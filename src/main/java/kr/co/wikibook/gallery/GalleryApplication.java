package kr.co.wikibook.gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 프레임워크는 MVC 구축에 필요한 모듈을 직접 설치하고, WAS를 개발자가 별도로 세팅하고 설정하여야 개발이 가능하였음.
// 이런 단점을 해결하고 비지니스 로직에만 집중하여 개발할수 있도록 스프링부트가 등장함, 스타터 팩을 제공하고 톰캣이라는 WAS를 기본적으로 포함함

@SpringBootApplication
public class GalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryApplication.class, args);
    }

}
