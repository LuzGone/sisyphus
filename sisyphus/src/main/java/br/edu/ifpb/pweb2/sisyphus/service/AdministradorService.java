package br.edu.ifpb.pweb2.sisyphus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sisyphus.model.Administrador;
import br.edu.ifpb.pweb2.sisyphus.model.User;
import br.edu.ifpb.pweb2.sisyphus.repository.AdministradorRepository;
import br.edu.ifpb.pweb2.sisyphus.repository.UserRepository;

@Service
public class AdministradorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public List<Administrador> getAdministradors(){
        return this.administradorRepository.findAll();
    }

    public Administrador getAdministradorPorId(Long id){
        return this.administradorRepository.findById(id).orElse(null);
    }

    public Administrador getAdministradorPorUsuario(String usuario){
        return this.administradorRepository.findByUsuario(usuario);
    }

    public Administrador salvarAdministrador(Administrador administrador){
        return this.administradorRepository.save(administrador);
    }

    public void deletarAdministrador(Long id){
        this.administradorRepository.deleteById(id);
    }

    public List<User> findEnabledUsers(){
        return userRepository.findByEnabledTrue();
    }
}
