package com.ymx.fbms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {

      /**
     * 用户id主键
     */
      private Integer userId;

      /**
     * 用户账户名
     */
      private String username;

      /**
     * 用户账户密码
     */
//      @JsonIgnore
      private String password;

      /**
     * 用户邮箱
     */
      private String userEmail;

      /**
     * 用户手机号
     */
      private String userPhone;

      /**
     * 用户地址
     */
      private String userAddress;

      /**
     * 用户身份
     */
      private String userRole;

      /**
       * 用户头像身份
       */
      private String userProfile;
}
