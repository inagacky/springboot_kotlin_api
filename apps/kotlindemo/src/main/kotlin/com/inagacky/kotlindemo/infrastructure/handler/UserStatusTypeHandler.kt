package com.inagacky.kotlindemo.infrastructure.handler

import com.inagacky.kotlindemo.domain.entity.sample.User
import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType

import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

/**
 * UserStatusクラスの変換ハンドラ
 */
class UserStatusTypeHandler : BaseTypeHandler<User.Status>() {

    @Throws(SQLException::class)
    override fun setNonNullParameter(ps: PreparedStatement, i: Int, parameter: User.Status, jdbcType: JdbcType) {
        ps.setInt(i, parameter.id)
    }

    @Throws(SQLException::class)
    override fun getNullableResult(rs: ResultSet, columnName: String): User.Status {
        return User.Status.fromId(rs.getInt(columnName))
    }

    @Throws(SQLException::class)
    override fun getNullableResult(rs: ResultSet, columnIndex: Int): User.Status {
        return User.Status.fromId(rs.getInt(columnIndex))
    }

    @Throws(SQLException::class)
    override fun getNullableResult(cs: CallableStatement, columnIndex: Int): User.Status {
        return User.Status.fromId(cs.getInt(columnIndex))
    }
}
