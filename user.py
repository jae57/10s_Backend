class User:
    def __init__(self, id, email, nickname, status, profile_image, token):
        self.id = id
        self.email = email
        self.nickname = nickname # 닉네임은 기본은 원래 이름을 사용한다.
        self.status = status
        self.profile_image = profile_image
        self.token = token