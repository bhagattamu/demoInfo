package com.bgurung.demoTest.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bgurung.demoTest.model.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> {

}
