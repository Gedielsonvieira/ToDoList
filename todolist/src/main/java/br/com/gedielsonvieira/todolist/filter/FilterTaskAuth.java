package br.com.gedielsonvieira.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gedielsonvieira.todolist.user.IUserRepository;
import br.com.gedielsonvieira.todolist.user.UserModel;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class FilterTaskAuth extends OncePerRequestFilter {

    private final IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if (servletPath.equals("/tasks")) {
            //Pegar usuario e senha
            String authorization = request.getHeader("Authorization");
            System.out.println(authorization);

            String authEncoded = authorization.substring("Basic".length()).trim();
            System.out.println(authEncoded);

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
            String usernameAndPassword = new String(authDecoded);
            System.out.println(usernameAndPassword);

            String[] credentials = usernameAndPassword.split(":");
            String username = credentials[0];
            String password = credentials[1];

            System.out.println(username);
            System.out.println(password);

            //Validar usuário
            UserModel user = iUserRepository.findByUsername(username);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                //Validar senha
                BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    //seque requisição
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }


    }

}
