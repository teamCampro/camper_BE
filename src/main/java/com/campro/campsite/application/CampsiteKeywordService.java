package com.campro.campsite.application;

import java.util.List;

public interface CampsiteKeywordService {
    void addKeyword(Long campsiteId, List<String> keywords);
}
