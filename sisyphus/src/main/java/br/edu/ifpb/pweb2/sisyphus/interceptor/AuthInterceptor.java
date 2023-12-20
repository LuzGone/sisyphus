package br.edu.ifpb.pweb2.sisyphus.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;
import br.edu.ifpb.pweb2.sisyphus.model.Aluno;
import br.edu.ifpb.pweb2.sisyphus.model.Coordenador;
import br.edu.ifpb.pweb2.sisyphus.model.Professor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean permissao = false;
        HttpSession session = request.getSession(false);

        if(session != null){
            if ((Administrador)session.getAttribute("administrador") != null){
                permissao = true;
            }else if ((Coordenador)session.getAttribute("coordenador") != null){
                permissao = true;
            }else if((Professor)session.getAttribute("professor") != null){
                permissao = true;
            }else if((Aluno)session.getAttribute("aluno") != null){
                permissao = true;
            }
            System.out.println("Sessão não nula"); 
        } else{
            System.out.println("Sessão nula");
            String urlBase = request.getContextPath();
            String paginaLogin = urlBase + "/auth";
            response.sendRedirect(response.encodeRedirectURL(paginaLogin));
            permissao = false;
        }
        return permissao;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
}
