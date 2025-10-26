
const courses = {
    'conversational-basic': {
        id: 1,
        title: '🗣️ Разговорный английский - Базовый',
        description: 'Начните говорить на английском с нуля. Идеально для начинающих.',
        price: '12 000 руб.',
        image: '🎯'
    },
    'conversational-advanced': {
        id: 2,
        title: '💬 Разговорный английский - Продвинутый',
        description: 'Улучшите fluency и уверенность в общении.',
        price: '15 000 руб.',
        image: '🚀'
    },
    'aviation-pilots': {
        id: 3,
        title: '✈️ Авиационный английский для пилотов',
        description: 'Стандарты ICAO, радиообмен, авиационная терминология.',
        price: '35 000 руб.',
        image: '🛩️'
    },
    'aviation-dispatchers': {
        id: 4,
        title: '🛫 Английский для диспетчеров',
        description: 'Управление воздушным движением, профессиональная лексика.',
        price: '32 000 руб.',
        image: '📡'
    }
};

// Открытие фильтра курсов
function openCourseFilter() {
    document.getElementById('courseFilter').classList.remove('hidden');
    document.querySelector('.course-filter-trigger').classList.add('hidden');
}



// Открытие модального окна
function openApplicationModal(courseId) {
    document.getElementById('applicationModal').classList.remove('hidden');
    document.getElementById('selectedCourseId').value = courseId;
    
    // Можно добавить название курса в заголовок модалки
    const course = Object.values(courses).find(c => c.id === courseId);
    if (course) {
        document.getElementById('modalTitle').textContent = `Заявка: ${course.title}`;
    }
}

function selectCourse(courseKey) {
    const course = courses[courseKey];
    
    const courseBanner = document.getElementById('courseBanner');
    courseBanner.innerHTML = `
        <div class="course-header">
            <span class="course-icon">${course.image}</span>
            <h3>${course.title}</h3>
            <p>${course.description}</p>
            <div class="course-price">${course.price}</div>
            
            <div class="course-actions">
                <button class="btn-apply" onclick="openApplicationModal(${course.id})">
                    Оставить заявку
                </button>
                <button class="btn-back" onclick="backToCourseSelection()">
                    ← Вернуться к выбору
                </button>
            </div>
        </div>
    `;
      
    document.getElementById('courseContent').classList.remove('hidden');
}

// Функция возврата к выбору курса
function backToCourseSelection() {
    // Скрываем контент курса
    document.getElementById('courseContent').classList.add('hidden');
    
    // Показываем кнопку выбора курса
    document.querySelector('.course-filter-trigger').classList.remove('hidden');
    
    // Скрываем фильтр (если он был открыт)
    document.getElementById('courseFilter').classList.add('hidden');
}

// Закрытие модального окна
function closeModal() {
    document.getElementById('applicationModal').classList.add('hidden');
}

// Отправка заявки - ВЕРСИЯ С ОТЛАДКОЙ
function submitApplication(event) {
    event.preventDefault();

    console.log('=== НАЧАЛО ОТПРАВКИ ЗАЯВКИ ===');

    const formData = new FormData(event.target);

    // Выводим все данные формы
    console.log('Данные формы:');
    for (let [key, value] of formData.entries()) {
        console.log('  ' + key + ': ' + value);
    }

    const submitBtn = event.target.querySelector('.btn-submit');
    const originalText = submitBtn.textContent;
    submitBtn.textContent = 'Отправка...';
    submitBtn.disabled = true;

    fetch('applications', {
        method: 'POST',
        body: new URLSearchParams(formData)
    })
        .then(response => {
            console.log('Статус ответа:', response.status);
            console.log('OK:', response.ok);
            console.log('URL:', response.url);
            return response.text();
        })
        .then(data => {
            console.log('Данные ответа:', data);

            if (data === 'OK') {
                alert('✅ Заявка успешно отправлена! Мы свяжемся с вами в течение 24 часов.');
                closeModal();
                event.target.reset();
            } else {
                alert('❌ Ошибка сервера: ' + data);
            }
        })
        .catch(error => {
            console.error('Ошибка fetch:', error);
            console.error('Тип ошибки:', error.name);
            console.error('Сообщение:', error.message);
            alert('❌ Ошибка сети: ' + error.message);
        })
        .finally(() => {
            submitBtn.textContent = originalText;
            submitBtn.disabled = false;
            console.log('=== ЗАВЕРШЕНИЕ ОТПРАВКИ ===');
        });
}

// Закрытие модалки по клику вне области
window.onclick = function(event) {
    const modal = document.getElementById('applicationModal');
    if (event.target === modal) {
        closeModal();
    }
}
