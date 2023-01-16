package com.ldm.order.dao;

import com.ldm.order.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
