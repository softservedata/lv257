package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.dto.PasswordDTO;
import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import com.softserve.edu.Resources.validator.UploadFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
@Controller
@Transactional
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    MainController mainController;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Environment env;
    //OK
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

//    http://localhost:8080/userEdit?uid=4
//    put at View #of user and send to Controller
    @RequestMapping(value = "/userEdit", params = {"uid"}, method = RequestMethod.GET)
    public ModelAndView userEdit(@RequestParam Map<String, String> queryUser) {
        Long userId = Long.parseLong(queryUser.get("uid"));
        System.out.println("useerID is " + userId);
        ModelAndView model = new ModelAndView("/userEdit");
        model.addObject("user", userService.getUserById(userId));
        model.addObject("uid", userId);
        return model;
    }

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
    public String accountPage(Model model) {
        PasswordDTO passwordDTO = new PasswordDTO();
        model.addAttribute("title", "Change Password");
        model.addAttribute("passwordDTO", passwordDTO);
        model.addAttribute("oldPassword", passwordDTO.getOldPassword());
        model.addAttribute("newPassword1", passwordDTO.getNewPassword1());
        model.addAttribute("newPassword1", passwordDTO.getNewPassword2());
        return "changePassword";
    }
    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.POST)
    public String accountPagePOST(
           @ModelAttribute("passwordOld") String passwordOld,
 BindingResult passwordOldResults,
            @ModelAttribute("newPassword1") String newPassword1,
           BindingResult newPassword11Results,
            @ModelAttribute("newPassword2") String newPassword2,
           BindingResult newPassword12Results,
            Model model)
            throws Exception {
        String userName = request.getUserPrincipal().getName();
        User user = userService.findByEmail(userName);
//        String passFromDB = user.getPassword();
        String passEncoded1 = passwordEncoder.encode(newPassword1);
        String passEncoded2 = passwordEncoder.encode(newPassword2);
//        user.setPassword(passEncoded2);
        model.addAttribute("newPassword1", passEncoded1);
        model.addAttribute("newPassword2", passEncoded1);
        model.addAttribute("title", "Change Password");
        return "changePassword";
    }



    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public ModelAndView usersDetails() {
        ModelAndView usersModel = new ModelAndView("userDetails");
        usersModel.addObject("users", userDetailsService.getAllUsers());
   /*     User u = new User();
        u.getUsername();*/
        return usersModel;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView usersModel = new ModelAndView("users");
        usersModel.addObject("users", userService.getAllUsers());
        return usersModel;
    }

    @RequestMapping(value = "/userEdit1", params = {"uid"}, method = RequestMethod.GET)
    public ModelAndView userEdit1(@RequestParam Map<String, String> queryUser) {
        Long userId = Long.parseLong(queryUser.get("uid"));
        System.out.println("useerID is " + userId);
        ModelAndView model = new ModelAndView("userEdit");
        model.addObject("user", userService.getUserById(userId));
        model.addObject("uid", userId);
        return model;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileGET0(ModelMap model, Principal principal)
//    public ModelAndView profileGET0(Model model, Principal principal, @RequestParam(value = "operation", required = false) String operation) {
    {
        if (principal == null) {
            model.addAttribute("message", "Please log in to see this page");
            return "403";
        } else {
            UserProfileDTO userProfileDTO = userProfileService.createUserProfileDTO(principal);
            Avatar nDocument = new Avatar();

            String userName = principal.getName();
            User user = userService.findByEmail(userName);
//            UserDetails userDetailsetails = userDetailsService.getUserDetailsByUserId(user.getId());
//            Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
//            model.addAttribute("details", details.isPresent() ? details.get() : new UserDetails());
            Collection<Role> roles = user.getRoles();
            /** todo improve code */
            /*String rolesString = null;
            for (Role role : roles) {
                rolesString = role.getName();
            }
//            rolesString = rolesString.substring(1, rolesString.length() - 1);
            System.out.println(rolesString);
//            String role = rolesString;*/
            String rolesString = null;
            Iterator iterator = user.getRoles().iterator();
            if (iterator.hasNext()) {
                Role role = (Role) iterator.next();
                rolesString = role.getName();
            }

            model.addAttribute("role", rolesString);
            model.addAttribute("details", userProfileDTO);
            model.addAttribute("document", nDocument);
            model.addAttribute("title", "Profile");
            /** add path to photo*/
//            model.addAttribute("photoBAD", env.getProperty("host.appUrl").concat("/").concat(rolesString).concat(".png"));
//            model.addAttribute("photo", "/resources/img/NoFoto.png");
            model.addAttribute("photo", "/resources/img/".concat(rolesString).concat(".png"));
//            model.addAttribute("photo", "contextPath/resources/img/".concat(rolesString).concat(".png"));
            return "profile";
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePOST0(
            @ModelAttribute(value = "details") @Valid UserProfileDTO userProfileDTO, BindingResult bindingResult,
            @ModelAttribute("document") Avatar document, BindingResult documentResults,
            @ModelAttribute("role") String rolesString, BindingResult roleResults,
            @ModelAttribute("photo") String photo, BindingResult photoResults,
            Model model)
            throws Exception {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "bindingResult.hasErrors");
            model.addAttribute("403");
        } else {
            model.addAttribute("title", "Profile");

            userProfileService.saveUserProfile(userProfileDTO);
        }
        model.addAttribute("details", userProfileDTO);
//           model.addAttribute("role", rolesString);
//           model.addAttribute("photo", photo);

        return "profile";
    }
    //rn - roleName
//    http://localhost:8080/roleInfo?rn=ROLE_ADMIN
    //TODO its not safe to show our role names in the URL so we need to ...
    @RequestMapping(value = "/roleInfo", params = {"rn"}, method = RequestMethod.GET)
    public ModelAndView roleInfo(@RequestParam Map<String, String> queryUser) {
        String roleName = queryUser.get("rn");
        ModelAndView model = new ModelAndView("administration/roleInfo");
        model.addObject("list", roleService.getRolePrivileges(roleName));
        model.addObject("roleName", roleName);
        return model;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ModelAndView roles() {
        ModelAndView rolesModel = new ModelAndView("roles");
        rolesModel.addObject("list", roleService.getAllRoles());
        return rolesModel;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addRole(@ModelAttribute("user") UserDTO user) {
        // TODO implement
        return users();
    }

    @RequestMapping(value = "/profile4040", method = RequestMethod.GET)
    public ModelAndView profileGET4040(Model model, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("404");
        modelAndView.addObject("message", "Please sign in");
        return modelAndView;
    }

    @RequestMapping(value="/profileAvatar", method= RequestMethod.GET)
    public String sendResourcesRequests(ModelMap mv
           , @RequestParam(value = "operation", required = false) String operation
)
{

        ResourceRequest nRequest = new ResourceRequest();
        Document nDocument = new Document();

        mv.addAttribute("mRequest", nRequest);
        mv.addAttribute("document", nDocument);
        mv.addAttribute("userClickSendRequest", true);
        mv.addAttribute("title", "Send Request");

        if(operation != null){
            if(operation.equals("request")){
                mv.addAttribute("message", "Request sent successfully!");
            }
        }

        return "profileAvatar";
    }

    @RequestMapping(value="/profileAvatar", method=RequestMethod.POST)
    public String handleRequestSubmission(
//            @Valid @ModelAttribute("mRequest") ResourceRequest mRequest,
//                                          BindingResult requestResults,
                                          @ModelAttribute("document") Document document,
                                          BindingResult documentResults, Model model
)
            throws Exception {

        //check if there are any errors
        new UploadFileValidator().validate(document, documentResults);

/*        if(requestResults.hasErrors() || documentResults.hasErrors()){*/
        if(documentResults.hasErrors()){
            model.addAttribute("userClickSendRequest", true);
            model.addAttribute("title", "Send Request");
            model.addAttribute("message", "Validation failed for sending request!");

            return "profileAvatar";
        }

//        logger.info(mRequest.toString());

//        documentService.fillUpDocument(document);
//        requestService.fillUpRequest(mRequest, document);

        return "redirect:profileAvatar";
    }

    public class FileController {

        private final Logger logger = LoggerFactory.getLogger(FileController.class);


        //unused yet
        @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
        @ResponseBody
        public String uploadFile(@RequestParam("file") MultipartFile file) {// имена параметров (тут - "file") - из формы JSP.

            String name = null;

            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    name = file.getOriginalFilename();

                    String rootPath = "C:\\path\\";  //try also "C:\path\"
                    File dir = new File(rootPath + File.separator + "loadFiles");

                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    stream.write(bytes);
                    stream.flush();
                    stream.close();

                    logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                    return "You successfully uploaded file=" + name;

                } catch (Exception e) {
                    return "You failed to upload " + name + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + name + " because the file was empty.";
            }
        }

    }
}