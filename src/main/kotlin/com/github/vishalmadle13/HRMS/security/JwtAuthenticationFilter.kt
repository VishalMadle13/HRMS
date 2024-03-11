import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.io.IOException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

     var  logger: Logger = LoggerFactory.getLogger(OncePerRequestFilter::class.java)

    @Autowired
    private lateinit var jwtHelper: JwtHelper

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val requestHeader = request.getHeader("Authorization")
        LoggerFactory.getLogger(OncePerRequestFilter::class.java).info(" Header :  {}", requestHeader?.orEmpty() ?: "Header is null")

        var username: String? = null
        var token: String? = null

        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7)
            try {
                username = jwtHelper.getUsernameFromToken(token)
            } catch (e: IllegalArgumentException) {
                LoggerFactory.getLogger(OncePerRequestFilter::class.java).info("Illegal Argument while fetching the username !!")
                e.printStackTrace()
            } catch (e: ExpiredJwtException) {
                LoggerFactory.getLogger(OncePerRequestFilter::class.java).info("Given jwt token is expired !!")
                e.printStackTrace()
            } catch (e: MalformedJwtException) {
                LoggerFactory.getLogger(OncePerRequestFilter::class.java).info("Some changes have been made in the token !! Invalid Token")
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
                username ="sagar"
            }
        } else {
             LoggerFactory.getLogger(OncePerRequestFilter::class.java).info("Invalid Header Value !! ")
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
            val validateToken: Boolean = token?.let { jwtHelper.validateToken(it, userDetails) } == true

            if (validateToken) {
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            } else {
                 LoggerFactory.getLogger(OncePerRequestFilter::class.java).info("Validation fails !!")
            }
        }
        println("Authorization::::::::::::"+username+","+token)
        filterChain.doFilter(request, response)
    }
}
