package dev.revaturemax.projections;

import dev.revaturemax.models.Tech;
import dev.revaturemax.models.Topic;

import java.util.Objects;

public class TopicDTO {

    public long topicId;
    public String techName;

    public TopicDTO(long topicId, String techName) {
        this.topicId = topicId;
        this.techName = techName;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicDTO topicDTO = (TopicDTO) o;
        return topicId == topicDTO.topicId && Objects.equals(techName, topicDTO.techName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, techName);
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "topicId=" + topicId +
                ", techName='" + techName + '\'' +
                '}';
    }
}
