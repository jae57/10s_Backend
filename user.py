class User:
    def __init__(self, id, email, nickname, status, profile_image, auth_token, auth_type):
        self.id = id
        self.email = email
        self.nickname = nickname # 닉네임은 기본은 원래 이름을 사용한다.
        self.status = status # 상태 - 자기 소개 같은거
        self.profile_image = profile_image
        self.auto_token = token
        self.auth_type = auth_type