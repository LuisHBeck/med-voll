package med.voll.api.domain.dtos.user;

import med.voll.api.domain.models.User;

public record UserListData(Long id, String username) {

    public UserListData(User user) {
        this(user.getId(), user.getUsername());
    }
}
