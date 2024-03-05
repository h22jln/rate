package myapp.rate.service;

import myapp.rate.domain.WriteForm;

import java.util.Map;

public interface BoardService {
    Map<String, String> getCordinate(String address);

    int contentSave(WriteForm form);
}
