<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/header.css">

<header class="main-header">
    <div class="header-container">
        
        <div class="header-logo">
            <a href="${pageContext.request.contextPath}/images/logo_sito.png" class="logo-link">
                <span class="logo-text">GAME <span class="accent-text">KEY</span> HUB</span>
            </a>
        </div>

        <input type="checkbox" id="menu-toggle" class="menu-toggle">
        <label for="menu-toggle" class="hamburger-label">
            <span></span>
            <span></span>
            <span></span>
        </label>

        <nav class="nav-menu">
            <ul class="nav-list">
                <li><a href="#" class="nav-link">Giochi PC</a></li>
                <li><a href="#" class="nav-link">Console</a></li>
                <li><a href="#" class="nav-link">Offerte</a></li>
                <li><a href="#" class="nav-link">Supporto</a></li>
            </ul>
        </nav>

        <div class="user-actions">
            <a href="#" class="action-link login-btn">
                <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                    <circle cx="12" cy="7" r="4"></circle>
                </svg>
                <span>Accedi</span>
            </a>
            <a href="#" class="action-link cart-btn">
                <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <circle cx="9" cy="21" r="1"></circle>
                    <circle cx="20" cy="21" r="1"></circle>
                    <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                </svg>
                <span>Carrello</span>
            </a>
        </div>
        
    </div>
</header>