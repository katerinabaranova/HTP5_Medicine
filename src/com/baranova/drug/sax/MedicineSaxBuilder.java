package com.baranova.drug.sax;


import com.baranova.drug.entity.Medicine;

import java.util.Set;

public class MedicineSaxBuilder {

    private Set<Medicine> students;
    private M sh;
        private
        XMLReader reader;
        public
        StudentsSAXBuilder() {
// создание SAX-анализатора
            sh          =
                    new
                            StudentHandler();
            try
            {
// создание объекта-обработчика
                reader = XMLReaderFactory.
                        createXMLReader
                                ();
                ИСПОЛЬЗОВАНИЕ КЛАССОВ И БИБЛИОТЕК
                430
                reader.setContentHandler(sh);
            }
            catch
                    (SAXException e) {
                System.
                        err
                        .print("ошибка SAX парсера: " + e);
            }
        }
        public
        Set<Student> getStudents() {
            return
                    students;
        }
        public
        void
        buildSetStudents(String fileName) {
            try
            {
// разбор XML-документа
                reader.parse(fileName);
            }
            catch
                    (SAXException e) {
                System.
                        err
                        .print("ошибка SAX парсера: " + e);
            }
            catch
                    (IOException e) {
                System.
                        err
                        .print("ошибка I/О потока: " + e);
            }
            students = sh.getStudents();
        }
    }
}
