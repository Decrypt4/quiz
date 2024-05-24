package com.example.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionProvider {
    public static List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questions = new ArrayList<>();

        questions.add(new QuizQuestion("Какой самый большой океан на Земле?", "Атлантический", "Тихий", "Индийский", "Южный", 2));
        questions.add(new QuizQuestion("Какая планета самая близкая к Солнцу?", "Венера", "Земля", "Меркурий", "Марс", 3));
        questions.add(new QuizQuestion("Как называется столица Австралии?", "Сидней", "Мельбурн", "Канберра", "Перт", 3));
        questions.add(new QuizQuestion("Какое животное является символом WWF?", "Тигр", "Слон", "Панда", "Лев", 3));
        questions.add(new QuizQuestion("Кто написал 'Гарри Поттер'?", "Дж.Р.Р. Толкин", "Дж. К. Роулинг", "Стивен Кинг", "Эдгар Аллан По", 2));
        questions.add(new QuizQuestion("Какой химический элемент обозначается символом 'O'?", "Золото", "Серебро", "Кислород", "Железо", 3));
        questions.add(new QuizQuestion("Как называется самый высокий водопад в мире?", "Ниагарский", "Анхель", "Виктория", "Игуасу", 2));
        questions.add(new QuizQuestion("Какое животное является самым быстрым?", "Гепард", "Лев", "Леопард", "Тигр", 1));

        questions.add(new QuizQuestion("В каком году человек впервые высадился на Луну?", "1965", "1967", "1969", "1971", 3));
        questions.add(new QuizQuestion("Какой язык является самым распространенным в мире?", "Английский", "Мандарин", "Испанский", "Хинди", 2));
        questions.add(new QuizQuestion("Какой континент самый большой по площади?", "Африка", "Азия", "Северная Америка", "Южная Америка", 2));
        questions.add(new QuizQuestion("Кто написал симфонию №9, известную как 'Ода к радости'?", "Бетховен", "Моцарт", "Бах", "Шуберт", 1));
        questions.add(new QuizQuestion("Какая страна известна как родина футбола?", "Франция", "Италия", "Бразилия", "Англия", 4));
        questions.add(new QuizQuestion("Как называется маленький кусочек земли, окруженный водой?", "Полуостров", "Атолл", "Архипелаг", "Остров", 4));
        questions.add(new QuizQuestion("Какое животное является символом США?", "Орёл", "Медведь", "Волк", "Буйвол", 1));
        questions.add(new QuizQuestion("Как называется газ, который делает пузыри в газированных напитках?", "Метан", "Этан", "Пропан", "Диоксид углерода", 4));
        questions.add(new QuizQuestion("Кто написал 'Войну и мир'?", "Достоевский", "Чехов", "Толстой", "Пушкин", 3));

        questions.add(new QuizQuestion("Какой химический элемент используется в термометрах?", "Цинк", "Ртуть", "Медь", "Свинец", 2));
        questions.add(new QuizQuestion("Какая планета в Солнечной системе самая большая?", "Земля", "Сатурн", "Юпитер", "Нептун", 3));
        questions.add(new QuizQuestion("Какое государство самое маленькое по площади?", "Монако", "Ватикан", "Науру", "Мальта", 2));
        questions.add(new QuizQuestion("Какой металл использовался для изготовления первой монеты?", "Золото", "Серебро", "Медь", "Железо", 3));
        questions.add(new QuizQuestion("Какой континент является самым холодным?", "Антарктида", "Северная Америка", "Азия", "Европа", 1));
        questions.add(new QuizQuestion("Кто написал 'Гамлет'?", "Шекспир", "Диккенс", "Хемингуэй", "Толстой", 1));
        questions.add(new QuizQuestion("Какая страна самая большая по населению?", "США", "Индия", "Китай", "Индонезия", 3));
        questions.add(new QuizQuestion("Какой фильм выиграл 'Оскар' за лучший фильм в 2020 году?", "1917", "Однажды в Голливуде", "Джокер", "Паразиты", 4));

        questions.add(new QuizQuestion(R.drawable.shimp, "Кто изображен на фото?", "Горилла", "Шимпанзе", "Орангутан", "Бонобо", 2));
        questions.add(new QuizQuestion(R.drawable.aus,"Чей это флаг?", "Франция", "Канада", "Италия", "Австралия", 4));
        questions.add(new QuizQuestion(R.drawable.ehy,"Как называется это строение?", "Золотая маска фараона", "Пирамиды Гизы", "Сфинкс", "Тутанхамон", 2));
        questions.add(new QuizQuestion(R.drawable.mclaren,"Выберите марку этого автомобиля", "Ferrari", "McLaren", "Mercedes", "Porsche", 2));

        return questions;
    }

    public static List<QuizQuestion> getRandomQuestions(int numberOfQuestions) {
        List<QuizQuestion> questions = getQuestions();
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(numberOfQuestions, questions.size()));
    }
}