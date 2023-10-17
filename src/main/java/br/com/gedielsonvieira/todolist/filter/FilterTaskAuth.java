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
        if (servletPath.startsWith("/tasks")) {
            //Pegar usuario e senha
            String authorization = request.getHeader("Authorization");

            String authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
            String usernameAndPassword = new String(authDecoded);


            String[] credentials = usernameAndPassword.split(":");
            String username = credentials[0];
            String password = credentials[1];

            //Validar usuário
            UserModel user = iUserRepository.findByUsername(username);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                //Validar senha
                BCrypt.Result passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (passwordVerify.verified) {
                    //setAttribute - Envia as informações do filtro para o controlador.
                    request.setAttribute("idUser", user.getId());
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
