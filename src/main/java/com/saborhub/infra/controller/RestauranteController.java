package com.saborhub.infra.controller;

import com.saborhub.application.dto.AtualizarItemCardapioDto;
import com.saborhub.application.dto.AtualizarRestauranteDto;
import com.saborhub.application.dto.ItemCardapioDto;
import com.saborhub.application.dto.RegistroItemCardapioDto;
import com.saborhub.application.dto.RegistroRestauranteDto;
import com.saborhub.application.dto.RestauranteDto;
import com.saborhub.application.usecases.AtualizarItemCardapioUseCase;
import com.saborhub.application.usecases.AtualizarRestauranteUseCase;
import com.saborhub.application.usecases.DeletarItemCardapioUseCase;
import com.saborhub.application.usecases.DeletarRestauranteUseCase;
import com.saborhub.application.usecases.ListarItensCardapioUseCase;
import com.saborhub.application.usecases.ListarRestaurantesUseCase;
import com.saborhub.application.usecases.ObterItemCardapioUseCase;
import com.saborhub.application.usecases.ObterRestauranteUseCase;
import com.saborhub.application.usecases.RegistrarItemCardapioUseCase;
import com.saborhub.application.usecases.RegistrarRestauranteUseCase;
import com.saborhub.domain.entities.ItemCardapio;
import com.saborhub.infra.persistence.RestauranteEntity;
import com.saborhub.infra.persistence.UsuarioEntity;
import com.saborhub.infra.repository.RestauranteRepository;
import com.saborhub.infra.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurante")
public class RestauranteController {
    private final ListarRestaurantesUseCase listar;
    private final ObterRestauranteUseCase obter;
    private final DeletarRestauranteUseCase deletar;
    private final RegistrarRestauranteUseCase registrar;
    private final AtualizarRestauranteUseCase atualizar;
    private final RegistrarItemCardapioUseCase registrarItemCardapio;
    private final ListarItensCardapioUseCase listarItensCardapio;
    private final ObterItemCardapioUseCase obterItemCardapio;
    private final AtualizarItemCardapioUseCase atualizarItemCardapio;
    private final DeletarItemCardapioUseCase deletarItemCardapio;
    private final RestauranteRepository repository;
    private final UsuarioRepository usuarioRepository;

    public RestauranteController(
            ListarRestaurantesUseCase listar,
            ObterRestauranteUseCase obter,
            DeletarRestauranteUseCase deletar,
            RegistrarRestauranteUseCase registrar,
            AtualizarRestauranteUseCase atualizar,
            RegistrarItemCardapioUseCase registrarItemCardapio,
            ListarItensCardapioUseCase listarItensCardapio,
            ObterItemCardapioUseCase obterItemCardapio,
            AtualizarItemCardapioUseCase atualizarItemCardapio,
            DeletarItemCardapioUseCase deletarItemCardapio,
            RestauranteRepository repository,
            UsuarioRepository usuarioRepository
    ) {
        this.listar = listar;
        this.obter = obter;
        this.deletar = deletar;
        this.registrar = registrar;
        this.atualizar = atualizar;
        this.registrarItemCardapio = registrarItemCardapio;
        this.listarItensCardapio = listarItensCardapio;
        this.obterItemCardapio = obterItemCardapio;
        this.atualizarItemCardapio = atualizarItemCardapio;
        this.deletarItemCardapio = deletarItemCardapio;
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('DONO_RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<RestauranteDto> cadastrarRestaurante(
            @RequestBody RegistroRestauranteDto dto,
            Authentication auth
    ) {
        String login = auth.getName();
        
        // Buscar o usuário pelo login para obter o ID correto
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityByLogin(login);
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        
        String donoId = usuario.getId();

        com.saborhub.domain.entities.Restaurante salvo = registrar.executar(dto, donoId);
        RestauranteDto body = new RestauranteDto(
                salvo.getId(),
                salvo.getCnpj(),
                salvo.getNome(),
                salvo.getEndereco(),
                salvo.getTipoCozinha(),
                salvo.getHorarioFuncionamento(),
                salvo.getDonoId()
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public List<RestauranteDto> listarRestaurantes() {
        return listar.obterTodosRestaurantes()
                .stream()
                .map(r -> {
                    RestauranteEntity e = repository.findAll()
                            .stream()
                            .filter(x -> x.getCnpj().equals(r.getCnpj()))
                            .findFirst()
                            .orElse(null);
                    return new RestauranteDto(
                            e != null ? e.getId() : null,
                            r.getCnpj(),
                            r.getNome(),
                            r.getEndereco(),
                            r.getTipoCozinha(),
                            r.getHorarioFuncionamento(),
                            r.getDonoId()
                    );
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestauranteDto> obterRestaurante(@PathVariable String id) {
        com.saborhub.domain.entities.Restaurante restaurante = obter.obterPorId(id);
        if (restaurante == null) {
            return ResponseEntity.notFound().build();
        }

        RestauranteDto body = new RestauranteDto(
                restaurante.getId(),
                restaurante.getCnpj(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                restaurante.getDonoId()
        );
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable String id) {
        deletar.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DONO_RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<RestauranteDto> atualizarRestaurante(
            @PathVariable String id,
            @RequestBody AtualizarRestauranteDto payload
    ) {
        com.saborhub.domain.entities.Restaurante salvo = atualizar.atualizar(id, payload);
        RestauranteDto body = new RestauranteDto(
                salvo.getId(),
                salvo.getCnpj(),
                salvo.getNome(),
                salvo.getEndereco(),
                salvo.getTipoCozinha(),
                salvo.getHorarioFuncionamento(),
                salvo.getDonoId()
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{restauranteId}/cardapio")
    public ResponseEntity<List<ItemCardapioDto>> listarItensCardapio(
            @PathVariable String restauranteId
    ) {
        // Verificar se o restaurante existe
        if (!repository.existsById(restauranteId)) {
            return ResponseEntity.notFound().build();
        }
        
        UUID restauranteUuid = UUID.fromString(restauranteId);
        List<ItemCardapio> itens = listarItensCardapio.listarPorRestaurante(restauranteUuid);
        
        List<ItemCardapioDto> itensDto = itens.stream()
                .map(item -> new ItemCardapioDto(
                        item.getId(),
                        item.getNome(),
                        item.getDescricao(),
                        item.getPreco(),
                        item.isDisponivelSomenteNoLocal(),
                        item.getCaminhoFoto(),
                        item.getRestauranteId()
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(itensDto);
    }

    @GetMapping("/{restauranteId}/cardapio/{itemId}")
    public ResponseEntity<ItemCardapioDto> obterItemCardapio(
            @PathVariable String restauranteId,
            @PathVariable String itemId
    ) {
        // Verificar se o restaurante existe
        if (!repository.existsById(restauranteId)) {
            return ResponseEntity.notFound().build();
        }
        
        try {
            UUID itemUuid = UUID.fromString(itemId);
            ItemCardapio item = obterItemCardapio.obterPorId(itemUuid);
            
            if (item == null) {
                return ResponseEntity.notFound().build();
            }
            
            // Verificar se o item pertence ao restaurante especificado
            UUID restauranteUuid = UUID.fromString(restauranteId);
            if (!item.getRestauranteId().equals(restauranteUuid)) {
                return ResponseEntity.notFound().build();
            }
            
            ItemCardapioDto itemDto = new ItemCardapioDto(
                    item.getId(),
                    item.getNome(),
                    item.getDescricao(),
                    item.getPreco(),
                    item.isDisponivelSomenteNoLocal(),
                    item.getCaminhoFoto(),
                    item.getRestauranteId()
            );
            
            return ResponseEntity.ok(itemDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{restauranteId}/cardapio")
    @PreAuthorize("hasRole('DONO_RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<ItemCardapioDto> adicionarItemCardapio(
            @PathVariable String restauranteId,
            @RequestBody RegistroItemCardapioDto dto,
            Authentication auth
    ) {
        // Verificar se o restaurante existe
        RestauranteEntity restaurante = repository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + restauranteId));
        
        // Verificar se o usuário autenticado é o dono do restaurante (exceto ADMIN)
        String login = auth.getName();
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityByLogin(login);
        
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        // Se não for ADMIN, verificar se é o dono do restaurante
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin && !restaurante.getDonoId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        UUID restauranteUuid = UUID.fromString(restauranteId);
        ItemCardapio itemSalvo = registrarItemCardapio.executar(dto, restauranteUuid);
        
        ItemCardapioDto responseDto = new ItemCardapioDto(
                itemSalvo.getId(),
                itemSalvo.getNome(),
                itemSalvo.getDescricao(),
                itemSalvo.getPreco(),
                itemSalvo.isDisponivelSomenteNoLocal(),
                itemSalvo.getCaminhoFoto(),
                itemSalvo.getRestauranteId()
        );
        
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{restauranteId}/cardapio/{itemId}")
    @PreAuthorize("hasRole('DONO_RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<ItemCardapioDto> atualizarItemCardapio(
            @PathVariable String restauranteId,
            @PathVariable String itemId,
            @RequestBody AtualizarItemCardapioDto dto,
            Authentication auth
    ) {
        // Verificar se o restaurante existe
        RestauranteEntity restaurante = repository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + restauranteId));
        
        // Verificar se o usuário autenticado é o dono do restaurante (exceto ADMIN)
        String login = auth.getName();
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityByLogin(login);
        
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        // Se não for ADMIN, verificar se é o dono do restaurante
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin && !restaurante.getDonoId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        UUID itemUuid = UUID.fromString(itemId);
        ItemCardapio itemAtualizado = atualizarItemCardapio.atualizar(itemUuid, dto);
        
        ItemCardapioDto responseDto = new ItemCardapioDto(
                itemAtualizado.getId(),
                itemAtualizado.getNome(),
                itemAtualizado.getDescricao(),
                itemAtualizado.getPreco(),
                itemAtualizado.isDisponivelSomenteNoLocal(),
                itemAtualizado.getCaminhoFoto(),
                itemAtualizado.getRestauranteId()
        );
        
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{restauranteId}/cardapio/{itemId}")
    @PreAuthorize("hasRole('DONO_RESTAURANTE') or hasRole('ADMIN')")
    public ResponseEntity<Void> deletarItemCardapio(
            @PathVariable String restauranteId,
            @PathVariable String itemId,
            Authentication auth
    ) {
        // Verificar se o restaurante existe
        RestauranteEntity restaurante = repository.findById(restauranteId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + restauranteId));
        
        // Verificar se o usuário autenticado é o dono do restaurante (exceto ADMIN)
        String login = auth.getName();
        UsuarioEntity usuario = usuarioRepository.findUsuarioEntityByLogin(login);
        
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        // Se não for ADMIN, verificar se é o dono do restaurante
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        
        if (!isAdmin && !restaurante.getDonoId().equals(usuario.getId())) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        UUID itemUuid = UUID.fromString(itemId);
        deletarItemCardapio.deletar(itemUuid);
        
        return ResponseEntity.noContent().build();
    }
}
