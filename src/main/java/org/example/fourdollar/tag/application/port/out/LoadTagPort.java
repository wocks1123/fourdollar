package org.example.fourdollar.tag.application.port.out;

import org.example.fourdollar.tag.domain.Tag;

import java.util.Optional;

public interface LoadTagPort {

    Optional<Tag> findById(Long id);

}
