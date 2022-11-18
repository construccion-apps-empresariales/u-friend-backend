package com.ufriend;

import com.ufriend.course.CourseEntity;
import com.ufriend.course.CourseService;
import com.ufriend.language.LanguageEntity;
import com.ufriend.language.LanguageService;
import com.ufriend.role.RoleEntity;
import com.ufriend.role.RoleService;
import com.ufriend.teacher.TeacherEntity;
import com.ufriend.teacher.TeacherService;
import com.ufriend.theme.ThemeEntity;
import com.ufriend.theme.ThemeService;
import com.ufriend.user.UserEntity;
import com.ufriend.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class UFriendApplication {

	@Resource
	private RoleService roleService;

	@Resource
	private ThemeService themeService;

	@Resource
	private LanguageService languageService;

	@Resource
	private UserService userService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private CourseService courseService;

	public static void main(String[] args) {
		SpringApplication.run(UFriendApplication.class, args);
	}

	@PostConstruct
	public void loadInitialData(){
		log.info("Creating test data");
		createLanguages();
		createRoles();
		createThemes();
		createUsers();
		createTeachers();
		createCourses();
	}

	private void createRoles(){
		log.info("Roles data");
		RoleEntity adminRole = roleService.findById("01");
		log.info("Roles data { AdminRole } from database: " + adminRole);
		if (adminRole == null){
			log.info("Roles data { AdminRole } does not exists, creating new one.");
			adminRole = new RoleEntity();
		}
		adminRole.setId("01");
		adminRole.setName("ADMIN");
		log.info("Roles data { AdminRole } created with data: " + adminRole);
		roleService.save(adminRole);

		log.info("Roles data { UserRole }");
		RoleEntity userRole = roleService.findById("02");
		log.info("Roles data { UserRole } from database: " + userRole);
		if (userRole == null){
			log.info("Roles data { UserRole } does not exists, creating a new one.");
			userRole = new RoleEntity();
		}
		userRole.setId("02");
		userRole.setName("USER");
		log.info("Roles data { UserRole } created with data: " + userRole);
		roleService.save(userRole);
	}

	private void createThemes(){
		log.info("Themes data");
		ThemeEntity lightTheme = themeService.findById("01");
		log.info("Themes data { LightTheme } from database: " + lightTheme);
		if (lightTheme == null){
			lightTheme = new ThemeEntity();
			log.info("Themes data { LightTheme } does not exist, creating a new one.");
		}
		lightTheme.setId("01");
		lightTheme.setName("LIGHT");
		lightTheme.setPrimary_color("#8032F5");
		lightTheme.setSecondary_color("#00A995");
		lightTheme.setSuccess_color("#04CBBD");
		lightTheme.setWarning_color("#FABE36");
		lightTheme.setDanger_color("#FF7878");
		log.info("Themes data { LightTheme } created with data: " + lightTheme);
		themeService.save(lightTheme);

		ThemeEntity darkTheme = themeService.findById("02");
		log.info("Themes data { DarkTheme } from database: " + lightTheme);
		if (darkTheme == null){
			darkTheme = new ThemeEntity();
			log.info("Themes data { DarkTheme } does not exist, creating a new one.");
		}
		darkTheme.setId("02");
		darkTheme.setName("DARK");
		//TODO: Set darkTheme colors
		darkTheme.setPrimary_color("#8032F5");
		darkTheme.setSecondary_color("#00A995");
		darkTheme.setSuccess_color("#04CBBD");
		darkTheme.setWarning_color("#FABE36");
		darkTheme.setDanger_color("#FF7878");
		log.info("Themes data { DarkTheme } created with data: " + darkTheme);
		themeService.save(darkTheme);
	}

	private void createLanguages(){
		LanguageEntity language = languageService.findById("EN");
		if (language == null){
			language = new LanguageEntity();
		}
		language.setId("EN");
		language.setName("ENGLISH");
		languageService.save(language);

		language = languageService.findById("ES");
		if (language == null){
			language = new LanguageEntity();
		}
		language.setId("ES");
		language.setName("SPANISH");
		languageService.save(language);
	}

	private void createUsers(){
		log.info("Users data");
		UserEntity adminUser = userService.findById(1L);
		log.info("Users data { AdminUser } from database: " + adminUser);
		if (adminUser == null){
			adminUser = new UserEntity();
			log.info("Users data { AdminUser } does not exist, creating a new one.");
		}
		adminUser.setId(1L);
		adminUser.setEmail("admin@ufriend.com");
		adminUser.setPassword("Asdf1234$");
		adminUser.setName("admin");
		adminUser.setConfirmed(true);
		adminUser.setRole(roleService.findById("01"));
		adminUser.setTheme(themeService.findById("01"));
		adminUser.setLanguage(languageService.findById("EN"));
		log.info("Users data { AdminUser } created with data: " + adminUser);
		userService.save(adminUser);

		log.info("Users data { TestUser }");
		UserEntity testUser = userService.findById(2L);
		log.info("Users data { TestUser } from database: " + testUser);
		if (testUser == null){
			testUser = new UserEntity();
			log.info("Users data { TestUser } does not exist, creating a new one.");
		}
		testUser.setId(2L);
		testUser.setEmail("user@ufriend.com");
		testUser.setPassword("Asdf1234$");
		testUser.setName("user");
		testUser.setConfirmed(true);
		testUser.setRole(roleService.findById("02"));
		testUser.setTheme(themeService.findById("01"));
		testUser.setLanguage(languageService.findById("ES"));
		log.info("Users data { TestUser } created with data: " + testUser);
		userService.save(testUser);
	}

	private void createTeachers() {
		log.info("Teachers data");
		TeacherEntity teacher = teacherService.findById(1L);
		log.info("Teachers data { TestTeacher } from database: " + teacher);
		if (teacher == null) {
			teacher = new TeacherEntity();
			log.info("Teachers data { TestTeacher } does not exist, creating a new one.");
		}
		teacher.setId(1L);
		teacher.setName("test");
		teacher.setLastname("teacher");
		teacher.setEmail("teacher@ufriend.com");
		log.info("Teachers data { TestTeacher } created with data: " + teacher);
		teacherService.save(teacher);
	}

	private void createCourses(){
		log.info("Courses data");
		CourseEntity course = courseService.findById(1L);
		log.info("Courses data { TestCourse } from database: " + course);
		if (course == null) {
			course = new CourseEntity();
			log.info("Courses data { TestCourse } does not exist, creating a new one.");
		}
		course.setId(1L);
		course.setName("test course");
		UserEntity user = userService.findById(1L);
		course.setUser(user);
		log.info("Courses data { TestCourse } created with data: " + course);
		courseService.save(course);
	}
}