package com.ddd.toy.pet.salon.application;

import com.ddd.toy.pet.salon.domain.PetRepository;
import com.ddd.toy.pet.salon.domain.User;
import com.ddd.toy.pet.salon.domain.UserRepository;
import com.ddd.toy.pet.salon.domain.option.Option;
import com.ddd.toy.pet.salon.domain.option.OptionArticle;
import com.ddd.toy.pet.salon.domain.option.OptionGroup;
import com.ddd.toy.pet.salon.dto.UserRequest;
import com.ddd.toy.pet.salon.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserService {
    private UserRepository userRepository;
    private PetRepository petRepository;

    public UserService(UserRepository userRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public UserResponse saveUser(UserRequest userRequest) {
        User persistUser = userRepository.save(userRequest.toUser());

        persistUser.createUserId();
        return UserResponse.from(persistUser);
    }

    public User findUserById(Long id) {
        if(id == 0L) {
            return null;
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }


    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void testChangePet() {
        List<OptionGroup> groups = setOptionGroups();
        List<OptionArticle> articles = setArticles();
        List<Option> options = setOptions();


        OptionGroup optionRes = new OptionGroup();

        options.forEach(o -> {
            List<String> articleArr = List.of(o.getOptionId().split("_"));

            optionRes.addArticles(articleArr, groups, articles);
        });

    }

    private List<Option> setOptions() {
        // 3가지 옵션 전체
        return List.of(new Option("성인/에르메스/브라운", "OA10_OA12_OA14")
                ,new Option("성인/에르메스/블루", "OA10_OA12_OA15")
                ,new Option("성인/폴로/블루", "OA10_OA13_OA15")
                ,new Option("소인/폴로/블루", "OA11_OA13_OA15")
        );

        // 2가지 옵션
//        return List.of(new Option("성인/에르메스/없음", "OA10_OA12_E0")
//                ,new Option("성인/에르메스/없음", "OA10_OA12_E0")
//                ,new Option("성인/폴로/없음", "OA10_OA13_E0")
//                ,new Option("소인/폴로/없음", "OA11_OA13_E0")
//        );
    }

    private List<OptionArticle> setArticles() {
        // 3가지 옵션 전체
        return List.of(new OptionArticle("성인", "OA10", 1)
                ,new OptionArticle("소인", "OA11", 1)
                ,new OptionArticle("에르메스", "OA12", 2)
                ,new OptionArticle("폴로", "OA13", 2)
                ,new OptionArticle("브라운", "OA14", 3)
                ,new OptionArticle("블루", "OA15", 3)
        );
    }

    private List<OptionGroup> setOptionGroups() {
        return List.of(new OptionGroup("선택1", 60L, 1)
        ,new OptionGroup("선택2", 61L, 2)
        ,new OptionGroup("선택3", 62L, 3));
    }
}
