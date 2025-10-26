<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home English School</title>
    <link rel="stylesheet" href="../../style.css">
</head>
<body>
<header class="header">
    <div class="header__container">
        <div class="header__left">
            <nav class="header__nav">
                <a href="#!">Главная</a>
                <a href="#!">Выбор курса</a>
                <a href="#!">Плюсы</a>
                <a href="#!">Процесс обучения</a>
                <a href="#!">Отзывы</a>
            </nav>
        </div>
        <div class="header__right">
            <div class="register">
                <button class="btn btn-register" onclick="location.href='sign_up'">Регистрация</button>
            </div>
            <div class="login">
                <button class="btn btn-login" onclick="location.href='login'">Войти</button>
            </div>
        </div>
    </div>
</header>
<main>
    <section class="welcome">
        <div class="container">
            <div class="subtitle__welcome">
                <h1 class="subtitle">Ваш путь к свободному английскому<br>начинается здесь!</h1>
            </div>
        </div>
    </section>
    <section class="about-courses" id="courses">
        <div class="container">
            <h2 class="section-subtitle">О курсах</h2>
            <p class="section-description">Выберите направление, которое подходит именно вам</p>
            <div class="course-filter-trigger">
                <button class="btn btn-filter" onclick="openCourseFilter()">
                    <span class="btn-filter-title">Выберите курс</span>
                </button>
            </div>
            <div class="course-filter hidden" id="courseFilter">
                <div class="filter-options">
                    <button class="filter-option" onclick="selectCourse('conversational-basic')">
                        <span class="icon">🗣️</span>
                        <span class="title">Разговорный английский - Базовый</span>
                        <span class="description">Для начинающих</span>
                    </button>
                    <button class="filter-option" onclick="selectCourse('conversational-advanced')">
                        <span class="icon">💬</span>
                        <span class="title">Разговорный английский - Продвинутый</span>
                        <span class="description">Для продолжающих</span>
                    </button>
                    <button class="filter-option" onclick="selectCourse('aviation-pilots')">
                        <span class="icon">✈️</span>
                        <span class="title">Авиационный английский для пилотов</span>
                        <span class="description">Стандарты ICAO</span>
                    </button>
                    <button class="filter-option" onclick="selectCourse('aviation-dispatchers')">
                        <span class="icon">🛫</span>
                        <span class="title">Английский для диспетчеров</span>
                        <span class="description">Управление воздушным движением</span>
                    </button>
                </div>
            </div>
            <div class="course-content hidden" id="courseContent">
                <div class="course-banner" id="courseBanner"></div>
            </div>
            <div id="applicationModal" class="modal hidden">
                <div class="modal-content">
                    <span class="close" onclick="closeModal()">&times;</span>
                    <h3 id="modalTitle">Оставить заявку</h3>
                    <form id="applicationForm" onsubmit="submitApplication(event)">
                        <input type="hidden" id="selectedCourseId" name="courseId">
                        <div class="form-group">
                            <label for="userName">Ваше имя *</label>
                            <input type="text" id="userName" name="name" required placeholder="Иван Петров">
                        </div>
                        <div class="form-group">
                            <label for="userEmail">Email *</label>
                            <input type="email" id="userEmail" name="email" required placeholder="ivan@example.com">
                        </div>
                        <div class="form-group">
                            <label for="userPhone">Телефон *</label>
                            <input type="tel" id="userPhone" name="phone" required placeholder="+7 (912) 345-67-89">
                        </div>
                        <div class="form-group">
                            <label for="userMessage">Ваши цели и пожелания</label>
                            <textarea id="userMessage" name="message" rows="4" placeholder="Расскажите о ваших целях в изучении английского..."></textarea>
                        </div>
                        <button type="submit" class="btn-submit">Отправить заявку</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <section class="Advantages">
        <h2 class="subtitle">Преимущетсва</h2>
        <div class="advantages__container">
            <div class="one__row">
                <div class="advantage-card">
                    <div class="advantage-icon">🎯</div>
                    <h3>Индивидуальный подход</h3>
                </div>
                <div class="advantage-card">
                    <div class="advantage-icon">🌍</div>
                    <h3>Опытные преподаватели</h3>
                </div>
                <div class="advantage-card">
                    <div class="advantage-icon">💬</div>
                    <h3>Разговорная практика</h3>
                </div>
            </div>
            <div class="two__row">
                <div class="advantage-card">
                    <div class="advantage-icon">✈️</div>
                    <h3>Авиационная специализация</h3>
                </div>
                <div class="advantage-card">
                    <div class="advantage-icon">🚀</div>
                    <h3>Полное сопровождение</h3>
                </div>
            </div>
        </div>
    </section>
    <section class="the__learning__process">
        <div class="the__learning__process__main">
            <h2 class="subtitle">Процесс обучения</h2>
            <div class="the__learning__process__container">
                <div class="block one__block">
                    <p class="number">01</p>
                    <h3 class="subtitle__block">Тестирование уровня</h3>
                    <p class="title">Определяем ваш текущий уровень и разрабатываем индивидуальный план обучения</p>
                </div>
                <div class="block two__block">
                    <p class="number">02</p>
                    <h3 class="subtitle__block">Разговорная практика</h3>
                    <p class="title">80% времени уделяем живому общению и практическому применению языка</p>
                </div>
                <div class="block three__block">
                    <p class="number">03</p>
                    <h3 class="subtitle__block">Контроль прогресса</h3>
                    <p class="title">Регулярно отслеживаем ваш прогресс и помогаем достичь поставленных целей</p>
                </div>
            </div>
        </div>
    </section>
    <section class="reviews">
        <h2 class="section-subtitle">Отзывы</h2>
    </section>
</main>
<footer></footer>
<script src="../../script.js"></script>
</body>
</html>
